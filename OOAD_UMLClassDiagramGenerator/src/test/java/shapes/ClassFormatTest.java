package shapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ClassFormatTest {
    private ClassFormat classFormat;
    private String className="mainTest";
    private int width=100;
    private int height=100;
    @Before
    public void setUp(){
        int x = 0;
        int y = 0;
        classFormat=new ConcreteFormat(className, x,y,width,height);
    }
    @Test
    public void contains() {
        assertTrue(classFormat.contains(new Point2D.Double(50,50)));
        assertFalse(classFormat.contains(new Point2D.Double(width+1,height+1)));
    }

    @Test
    public void setLocation() {
        classFormat.setLocation(400,400);
        assertEquals(new Point(400,400),classFormat.getLocation());
    }

    @Test
    public void getClassName() {
        assertEquals(className,classFormat.getClassName());
    }

    @Test
    public void setClassName() {
        String newClassName="RelationTest";
        classFormat.setClassName(newClassName);
        assertEquals(newClassName,classFormat.getClassName());
    }

    @Test
    public void getMethods() {
        String []methods={"test()","main()"};
        classFormat.setMethods(Arrays.asList(methods));
        assertArrayEquals(methods,classFormat.getMethods().toArray());
    }

    @Test
    public void setMethods() {
        String []methods={"test(int a)","main(String[]args)"};
        classFormat.setMethods(Arrays.asList(methods));
        assertArrayEquals(methods,classFormat.getMethods().toArray());
    }

    @Test
    public void getVariables() {
        String []variables={"int i","String className"};
        classFormat.setVariables(Arrays.asList(variables));
        assertArrayEquals(variables,classFormat.getVariables().toArray());
    }

    @Test
    public void setVariables() {
        String []variables={"UMLClassDiagram diagram","String gp"};
        classFormat.setVariables(Arrays.asList(variables));
        assertArrayEquals(variables,classFormat.getVariables().toArray());
    }
}