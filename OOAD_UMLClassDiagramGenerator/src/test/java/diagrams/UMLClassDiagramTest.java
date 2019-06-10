package diagrams;

import generator.ClassRelationGenerator;
import generator.ClassUnitGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shapes.ClassFormat;
import shapes.Relation;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class UMLClassDiagramTest {
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
        relation= classRelationGenerator.generateRelation(startClass,endClass,RelationType.Association);
    }
    @Test
    public void getWidth() {
        assertEquals(width,diagram.getWidth());
    }

    @Test
    public void setWidth() {
        diagram.setWidth(0);
        assertEquals(0,diagram.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(height,diagram.getHeight());
    }

    @Test
    public void setHeight() {
        diagram.setHeight(-50);
        assertEquals(-50,diagram.getHeight());
    }

    @Test
    public void addRelationToDiagram() {
        diagram.addToDiagram(relation);
        Iterator<Relation> iterator=diagram.createRelationIterator();
        assertEquals(relation,iterator.next());
    }

    @Test
    public void addClassFormatToDiagram()throws Exception {
        diagram.addToDiagram(startClass);
        assertSame(startClass, diagram.getClassFormat(startClass.getClassName()));
    }

    @Test
    public void setClassFormatLocation() throws Exception{
        Point expectLocation=new Point(50,50);
        diagram.addToDiagram(endClass);
        diagram.setClassFormatLocation(endClassName,expectLocation.x,expectLocation.y);
        assertEquals(expectLocation,diagram.getClassFormat(endClassName).getLocation());
    }

    @Test
    public void createClassFormatIterator() {
        diagram.addToDiagram(startClass);
        ClassFormat actual=diagram.createClassFormatIterator().next().getValue();
        assertSame(startClass, actual);
    }

    @Test
    public void createRelationIterator() {
        diagram.addToDiagram(relation);
        Relation actual=diagram.createRelationIterator().next();
        assertEquals(relation,actual);
    }

    @Test(expected = Exception.class)
    public void getClassFormat() throws Exception{
        diagram.addToDiagram(endClass);
        assertSame(endClass,diagram.getClassFormat(endClass.getClassName()));
        assertNull(diagram.getClassFormat(startClass.getClassName()));
    }

    @Test(expected = Exception.class)
    public void removeClassFormat() throws Exception{
        diagram.addToDiagram(startClass);
        diagram.removeClassFormat(startClass);
        assertNull(diagram.getClassFormat(startClass.getClassName()));
    }

    @Test
    public void removeRelation() {
        diagram.addToDiagram(relation);
        diagram.removeRelation(relation);
        Boolean actual=diagram.createRelationIterator().hasNext();
        assertEquals(false,actual);
    }

}