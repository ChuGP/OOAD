package generator;
import diagrams.RelationType;
import diagrams.UMLClassDiagram;
import shapes.ClassFormat;
import shapes.Relation;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class ArrangeCalculator {
    private UMLClassDiagram diagram;
    public ArrangeCalculator(UMLClassDiagram diagram) {
        this.diagram = diagram;
    }

    public void arrange(){
        int width=diagram.getWidth(),startX=50,offsetX=300,offsetY=300;
        int x=startX,y=50;
        Iterator<Map.Entry<String,ClassFormat>> classFormatIterator=diagram.createClassFormatIterator();
        while(classFormatIterator.hasNext()){
            if(x+offsetX>=width) {
                y += offsetY;
                x=startX;
            }
            ClassFormat format=classFormatIterator.next().getValue();
            format.setLocation(x,y);
            x+=offsetX;
        }
    }

    public void removeRelation(Point location){
        int difference=5;
        Iterator<Relation>relationIterator=diagram.createRelationIterator();
        List<Relation> possibleRelations=new ArrayList<>();
        while(relationIterator.hasNext()){
            Relation relation=relationIterator.next();
            if(relation.contains(location))
                possibleRelations.add(relation);
        }
        if(!possibleRelations.isEmpty()){
            for(Relation relation:possibleRelations){
                Point2D result=solveEquation(new Point(relation.getStartX(),relation.getStartY()),new Point(relation.getEndX(),relation.getEndY()));
                int expectY=(int)(result.getX()*location.x+result.getY());
                if(expectY-difference<=location.y && location.y<=expectY+difference){
                    diagram.removeRelation(relation);
                    break;
                }
            }
        }
    }

    public void removeUnit(Point location){
        ClassFormat classFormat=checkPointContains(location);
        diagram.removeClassFormat(classFormat);
    }

    public ClassFormat checkPointContains(Point point){
        ClassFormat containClass=null;
        Iterator<Map.Entry<String,ClassFormat>> classFormatIterator=diagram.createClassFormatIterator();
        while (classFormatIterator.hasNext()){
            ClassFormat classFormat=classFormatIterator.next().getValue();
            if(classFormat.contains(point))
                containClass=classFormat;
        }
        return containClass;
    }

    public void linkTwoUnitWithMinDistance(Point start,Point end,ClassFormat startClass,ClassFormat endClass,Relation relation){
        Point2D equation=solveEquation(start,end);
        List<Point2D>startClassResult=getPossibleSolution(equation,startClass);
        startClassResult=getRecContainsPoint(startClassResult,startClass);
        List<Point2D>endClassResult=getPossibleSolution(equation,endClass);
        endClassResult=getRecContainsPoint(endClassResult,endClass);
        try {
            List<Point2D> result = getMinStartEndPoint(startClassResult, endClassResult);
            Point2D startPoint = result.get(0), endPoint = result.get(1);
            relation.setStartX((int) startPoint.getX());
            relation.setStartY((int) startPoint.getY());
            relation.setEndX((int) endPoint.getX());
            relation.setEndY((int) endPoint.getY());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void bindRelation(Point start, Point end,RelationType relationType){
        ClassFormat startClass=checkPointContains(start),endClass=checkPointContains(end);
        if(startClass !=null && endClass!=null){
            ClassRelationGenerator relationGenerator=new ClassRelationGenerator();
            Relation relation=relationGenerator.generateRelation(startClass,endClass, relationType);
            linkTwoUnitWithMinDistance(start,end,startClass,endClass,relation);
            diagram.addToDiagram(relation);
        }
    }

    public void moveUnit(Point start , Point end){
        ClassFormat startClass=null;
        Iterator<Map.Entry<String,ClassFormat>> classFormatIterator=diagram.createClassFormatIterator();
        while (classFormatIterator.hasNext()){
            ClassFormat classFormat=classFormatIterator.next().getValue();
            if(classFormat.contains(start))
                startClass = classFormat;
        }
        if(startClass!=null) {
            int offsetX = end.x - start.x, offsetY = end.y - start.y;
            int newX=startClass.x+offsetX,newY=startClass.y+offsetY;
            try {
                diagram.setClassFormatLocation(startClass.getClassName(), newX, newY);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    Point2D solveEquation(Point start, Point end){
        double a=(start.getY()-end.getY())/(start.getX()-end.getX()),b=(end.getY()*start.getX()-start.getY()*end.getX())/(start.getX()-end.getX());
        Point2D p=new Point2D.Double(a,b);
        return p;
    }

    List<Point2D> getRecContainsPoint(List<Point2D> possibleSolution,ClassFormat classFormat){
        List<Point2D> result=new ArrayList<>();
        for(Point2D p:possibleSolution) {
            if (classFormat.contains(p))
                result.add(p);
        }
        return result;
    }

    List<Point2D> getPossibleSolution(Point2D equation, ClassFormat classFormat){
        String axis="xyxy";
        double a=equation.getX(),b=equation.getY();
        int[]value={classFormat.x,classFormat.y,classFormat.x+classFormat.width,classFormat.y+classFormat.height};
        List<Point2D> solution=new ArrayList<>();
        for(int i=0;i<axis.length();i++){
            double x=0,y=0;
            if(axis.charAt(i)=='x'){
                x=value[i];
                y=a*x+b;
            }
            else if(axis.charAt(i)=='y'){
                y=value[i];
                x=(y-b)/a;
            }
            Point2D result=new Point2D.Double(x,y);
            solution.add(result);
        }
        return solution;
    }

    List<Point2D> getMinStartEndPoint(List<Point2D>startClassResult,List<Point2D>endClassResult)throws Exception{
        if(startClassResult.isEmpty() || endClassResult.isEmpty())
            throw new Exception("The Two List should not be empty");
        Point2D minStart=new Point(0,0),minEnd=new Point(0,0);
        List<Point2D> minStartEnd=new ArrayList<>();
        double minDistance=Double.MAX_VALUE;
        for(Point2D startP:startClassResult){
            for (Point2D endP:endClassResult){
                double distance=Point.distance(startP.getX(),startP.getY(),endP.getX(),endP.getY());
                if(distance<=minDistance){
                    minStart=startP;
                    minEnd=endP;
                    minDistance=distance;
                }
            }
        }
        minStartEnd.add(minStart);
        minStartEnd.add(minEnd);
        return minStartEnd;
    }
}
