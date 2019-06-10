package shapes;

import diagrams.RelationType;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class RelationTest {
    private ClassFormat startClass,endClass;
    private String startClassName ="mainTest",endClassName="main";
    private RelationType relationType=RelationType.Composition;
    Relation relation;
    private int width=100;
    private int height=100;
    @Before
    public void setUp(){
        int x = 0;
        int y = 0;
        startClass =new ConcreteFormat(startClassName, x,y,width,height);
        endClass =new ConcreteFormat(endClassName, x,y,width,height);
        relation=new Relation(startClass,endClass,relationType);
    }

    @Test
    public void getStartClass() {
        assertSame(startClass,relation.getStartClass());
    }

    @Test
    public void getEndClass() {
        assertSame(endClass,relation.getEndClass());
    }

    @Test
    public void setStartX() {
        int startX=100;
        relation.setStartX(startX);
        assertEquals(startX,relation.getStartX());
    }

    @Test
    public void setEndX() {
        int endX=100;
        relation.setEndX(endX);
        assertEquals(endX,relation.getEndX());
    }

    @Test
    public void setStartY() {
        int startY=100;
        relation.setStartY(startY);
        assertEquals(startY,relation.getStartY());
    }

    @Test
    public void setEndY() {
        int endY=100;
        relation.setEndY(endY);
        assertEquals(endY,relation.getEndY());
    }

    @Test
    public void setStartClass() {
        ClassFormat startClassTest =new ConcreteFormat(startClassName+"_testdata", 60,80,width,height);
        relation.setStartClass(startClassTest);
        assertSame(startClassTest,relation.getStartClass());
    }

    @Test
    public void setEndClass() {
        ClassFormat endClassTest =new ConcreteFormat(endClassName+"_testdata", 60,80,width,height);
        relation.setEndClass(endClassTest);
        assertSame(endClassTest,relation.getEndClass());
    }

    @Test
    public void setRelationType() {
        RelationType testType=RelationType.Dependency;
        relation.setRelationType(testType);
        assertEquals(testType,relation.getRelationType());
    }

    @Test
    public void getRelationType() {
        assertEquals(relationType,relation.getRelationType());
    }

    @Test
    public void contains(){
        relation.setStartX(0);
        relation.setEndX(100);
        relation.setStartY(0);
        relation.setEndY(100);
        assertEquals(true,relation.contains(new Point(50,70)));
        assertEquals(false,relation.contains(new Point(101,70)));
    }
}