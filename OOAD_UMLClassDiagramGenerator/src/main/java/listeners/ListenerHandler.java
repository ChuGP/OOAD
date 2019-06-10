package listeners;

import diagrams.UMLClassDiagram;
import generator.ArrangeCalculator;
import output.DiagramReader;
import output.DiagramWriter;

import java.awt.*;
import java.io.IOException;

public class ListenerHandler {
    private UMLClassDiagram diagram;
    private String autoSavePath;
    private ArrangeCalculator arrangeCalculator;
    public ListenerHandler(UMLClassDiagram diagram){
        this.autoSavePath=System.getProperty("user.dir")+"/Untitled_diagram.diagram";
        this.diagram=diagram;
        arrangeCalculator=new ArrangeCalculator(diagram);
    }

    public void executeAddRelation(Point start, Point end){
        arrangeCalculator.bindRelation(start,end);
        executeSaveDiagram(autoSavePath);
    }

    public void executeMoveUnit(Point start , Point end){
        arrangeCalculator.moveUnit(start,end);
        executeSaveDiagram(autoSavePath);
    }

    public void executeRemoveUnit(Point location){
        arrangeCalculator.removeUnit(location);
        executeSaveDiagram(autoSavePath);
    }

    public void executeRemoveRelation(Point location){
        arrangeCalculator.removeRelation(location);
        executeSaveDiagram(autoSavePath);
    }

    public void executeSaveDiagram(String path){
        autoSavePath=path;
        DiagramWriter diagramWriter =new DiagramWriter(diagram);
        try {
            diagramWriter.save(path);
        }catch (IOException ioe){
            throw new RuntimeException("File didn't close");
        }
    }

    public void executeOpenDiagram(String path){
        try {
            DiagramReader diagramReader = new DiagramReader(path);
            diagram.setDiagram(diagramReader.getDiagram());
        }catch (Exception e){
            throw new RuntimeException("File not found");
        }


    }
}
