package adapter;

import org.junit.Before;
import org.junit.Test;
import shapes.ClassFormat;
import shapes.ConcreteFormat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassFormatOutputDTOTest {
    private ClassFormat classFormat;
    private String className="mainTest";
    private int width=100;
    private int height=100;
    @Before
    public void setUp(){
        int x = 0;
        int y = 0;
        classFormat=new ConcreteFormat(className, x,y,width,height);
        classFormat.setMethods(new ArrayList<>());
        classFormat.setVariables(new ArrayList<>());
    }
    @Test
    public void getOutput() {
        ClassFormatOutputDTO classFormatOutputDTO=new ClassFormatOutputDTO(classFormat);
        assertEquals("ClassName:mainTest;Method:;Variable:;StartPoint:0,0;HeightWidth:100,100",classFormatOutputDTO.getOutput());
    }
}