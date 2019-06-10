package output;

import diagrams.RelationType;
import diagrams.UMLClassDiagram;
import generator.ClassRelationGenerator;
import generator.ClassUnitGenerator;
import shapes.ClassFormat;
import shapes.Relation;


import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class DiagramReader {
    private UMLClassDiagram diagram;
    public DiagramReader(String path)throws Exception{
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width=(int)d.getWidth(),height=(int)d.getHeight();
        diagram=new UMLClassDiagram(width,height);
        readDiagram(path);
    }

    public void readDiagram(String path)throws Exception {
        BufferedReader br=new BufferedReader(new FileReader(new File(path)));
        String line="";
        List<String> lineData=new ArrayList<>();
        Map<String,Map<String,String>> allData=new HashMap<>();
        while((line=br.readLine())!=null)
            lineData.add(line);
        for(int i=0;i<lineData.size();i++) {
            String[] attributes = lineData.get(i).split(";");
            Map<String, String> dataDetail = new HashMap<>();
            for (String attr : attributes) {
                String[] attrPair = attr.split(":");
                if(attrPair.length>1)
                    dataDetail.put(attrPair[0], attrPair[1]);
                else
                    dataDetail.put(attrPair[0],"");
            }
            allData.put(String.valueOf(i), dataDetail);
        }
        convertDiagram(allData);
    }

    private void convertDiagram(Map<String,Map<String,String>> diagramData){
        ClassUnitGenerator classUnitGenerator=new ClassUnitGenerator();
        ClassRelationGenerator classRelationGenerator=new ClassRelationGenerator();
        for(Map.Entry<String,Map<String,String>>attributes:diagramData.entrySet()){
            Map<String,String>attribute=attributes.getValue();
            if (attribute.containsKey("ClassName")){
                String className=attribute.get("ClassName");
                String[]method=(attribute.get("Method").contains(",")?attribute.get("Method").split(","):new String[]{});
                String[]variable=(attribute.get("Variable").contains(",")?attribute.get("Variable").split(","):new String[]{});
                List<String>methods=Arrays.asList(method);
                List<String>variables=Arrays.asList(variable);
                String[]heightWidth=attribute.get("HeightWidth").split(",");
                String[]startPoint=attribute.get("StartPoint").split(",");
                classUnitGenerator.setClassAttributes(className,methods,variables);
                ClassFormat classFormat=classUnitGenerator.generateConcreteClassFormat();
                classFormat.setSize(Integer.parseInt(heightWidth[1]),Integer.parseInt(heightWidth[0]));
                classFormat.setLocation(Integer.parseInt(startPoint[0]),Integer.parseInt(startPoint[1]));
                diagram.addToDiagram(classFormat);
            }
            else if(attribute.containsKey("StartClass")){
                String startClass=attribute.get("StartClass");
                String endClass=attribute.get("EndClass");
                try {
                    Relation relation=classRelationGenerator.generateRelation(diagram.getClassFormat(startClass),diagram.getClassFormat(endClass), RelationType.valueOf(attribute.get("Type")));
                    String[]startPoint=attribute.get("StartPoint").split(",");
                    String[]endPoint=attribute.get("EndPoint").split(",");
                    relation.setStartX(Integer.parseInt(startPoint[0]));
                    relation.setStartY(Integer.parseInt(startPoint[1]));
                    relation.setEndX(Integer.parseInt(endPoint[0]));
                    relation.setEndY(Integer.parseInt(endPoint[1]));
                    diagram.addToDiagram(relation);
                }catch (Exception e){
                    throw new RuntimeException("The Relation start or end class not found");
                }
            }
        }
    }

    public UMLClassDiagram getDiagram() {
        return diagram;
    }
}
