package output;

import diagrams.RelationType;
import diagrams.UMLClassDiagram;
import generator.ClassRelationGenerator;
import generator.ClassUnitGenerator;
import org.junit.Before;
import org.junit.Test;
import shapes.ClassFormat;
import shapes.Relation;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DiagramWriterTest {
    private UMLClassDiagram diagram;
    private final String[]methods={"testMethod()","main()"},variables={"int tag","String name"};
    private final String startClassName ="main",endClassName="UMLClassDiagram";
    private ClassFormat startClass,endClass;
    private Relation relation;
    private final int width=500,height=500;
    @Before
    public void setUp(){
        diagram=new UMLClassDiagram(width,height);
        ClassUnitGenerator classUnitGenerator = new ClassUnitGenerator();
        ClassRelationGenerator classRelationGenerator = new ClassRelationGenerator();
        classUnitGenerator.setClassAttributes(startClassName, Arrays.asList(methods),Arrays.asList(variables));
        startClass=classUnitGenerator.generateConcreteClassFormat();
        classUnitGenerator.setClassAttributes(endClassName, Arrays.asList(methods),Arrays.asList(variables));
        endClass=classUnitGenerator.generateConcreteClassFormat();
        relation= classRelationGenerator.generateRelation(startClass,endClass, RelationType.Association);
        diagram.addToDiagram(startClass);
        diagram.addToDiagram(endClass);
        diagram.addToDiagram(relation);
    }
    @Test
    public void save() throws Exception{
        String path=System.getProperty("user.dir")+"/tmp.diagram";
        DiagramWriter diagramWriter =new DiagramWriter(diagram);
        diagramWriter.save(path);
        DiagramReader diagramReader=new DiagramReader(path);
        UMLClassDiagram newDiagram=diagramReader.getDiagram();
//        newDiagram.getClassFormat("main");
        assertEquals(diagram.getClassFormat("main"),newDiagram.getClassFormat("main"));
        assertEquals(diagram.getClassFormat("main").getMethods(),newDiagram.getClassFormat("main").getMethods());
//        FileWriter fw=new FileWriter(new File(System.getProperty("user.dir")+"/test.txt"));
//        fw.write("12345");
//        fw.write("\r\n");
//        fw.write("12345");
//        fw.close();
    }
    @Test
    public void test(){
        String s="";
        s.contains(",");
    }
}