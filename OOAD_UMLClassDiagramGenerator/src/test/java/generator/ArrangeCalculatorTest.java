package generator;

import diagrams.UMLClassDiagram;
import generator.ArrangeCalculator;
import shapes.ClassFormat;
import shapes.ConcreteFormat;
import org.junit.Test;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrangeCalculatorTest {

    @Test
    public void arrange() {
    }

    @Test
    public void calculateArrange() {
    }

    @Test
    public void moveUnit() {

    }

    @Test
    public void solveEquation(){
        UMLClassDiagram classDiagram=new UMLClassDiagram(500,500);
        ArrangeCalculator arrangeCalculator=new ArrangeCalculator(classDiagram);
        Point start=new Point(60,60),end=new Point(480,270);
        Point2D slopeAndOffset=arrangeCalculator.solveEquation(start,end);
        assertEquals(0.5,slopeAndOffset.getX(),0.1);
        assertEquals(30,slopeAndOffset.getY(),0.1);
    }

    @Test
    public void solveEquation1(){
        UMLClassDiagram classDiagram=new UMLClassDiagram(500,500);
        ArrangeCalculator arrangeCalculator=new ArrangeCalculator(classDiagram);
        Point start=new Point(60,60),end=new Point(240,480);
        Point2D slopeAndOffset=arrangeCalculator.solveEquation(start,end);
        assertEquals(2.33,slopeAndOffset.getX(),0.1);
        assertEquals(-80,slopeAndOffset.getY(),0.1);

    }

    @Test
    public void getPossibleSolution(){
        UMLClassDiagram classDiagram=new UMLClassDiagram(500,500);
        ClassFormat startClass=new ConcreteFormat("StartClass",10,10,100,100);
        ClassFormat  endClass=new ConcreteFormat("EndClass",190,430,100,100);
        classDiagram.addToDiagram(startClass);
        classDiagram.addToDiagram(endClass);
        ArrangeCalculator arrangeCalculator=new ArrangeCalculator(classDiagram);
        Point start=new Point(60,60),end=new Point(240,480);
        Point2D slopeAndOffset=arrangeCalculator.solveEquation(start,end);

        List<Point2D>startClassResult=arrangeCalculator.getPossibleSolution(slopeAndOffset,startClass);
        assertEquals(new Point2D.Double(10.0,-56.666666666666664),startClassResult.get(0));
        assertEquals(new Point2D.Double(38.57142857142857,10.0),startClassResult.get(1));
    }

    @Test
    public void getRecContainsPoint(){
        UMLClassDiagram classDiagram=new UMLClassDiagram(500,500);
        ClassFormat startClass=new ConcreteFormat("StartClass",10,10,100,100);
        ClassFormat  endClass=new ConcreteFormat("EndClass",190,430,100,100);
        classDiagram.addToDiagram(startClass);
        classDiagram.addToDiagram(endClass);
        ArrangeCalculator arrangeCalculator=new ArrangeCalculator(classDiagram);
        Point start=new Point(60,60),end=new Point(240,480);
        Point2D slopeAndOffset=arrangeCalculator.solveEquation(start,end);

        List<Point2D>startClassResult=arrangeCalculator.getPossibleSolution(slopeAndOffset,startClass);
        startClassResult=arrangeCalculator.getRecContainsPoint(startClassResult,startClass);
        assertEquals(true,startClass.contains(startClassResult.get(0)));
        assertEquals(true,startClass.contains(startClassResult.get(1)));

        List<Point2D>endClassResult=arrangeCalculator.getPossibleSolution(slopeAndOffset,endClass);
        endClassResult=arrangeCalculator.getRecContainsPoint(endClassResult,endClass);
        assertEquals(true,endClass.contains(endClassResult.get(0)));
        assertEquals(true,endClass.contains(endClassResult.get(1)));
    }

    @Test
    public void getMinStartEndPoint()throws Exception{
        UMLClassDiagram classDiagram=new UMLClassDiagram(500,500);
        ArrangeCalculator arrangeCalculator=new ArrangeCalculator(classDiagram);
        List<Point2D> start=new ArrayList<>();
        List<Point2D> end=new ArrayList<>();
        start.add(new Point(45,10));
        start.add(new Point(95,110));
        end.add(new Point(255,430));
        end.add(new Point(290,500));
        List<Point2D> result = arrangeCalculator.getMinStartEndPoint(start, end);

        assertEquals(new Point(95,110),result.get(0));
        assertEquals(new Point(255,430),result.get(1));
    }
}