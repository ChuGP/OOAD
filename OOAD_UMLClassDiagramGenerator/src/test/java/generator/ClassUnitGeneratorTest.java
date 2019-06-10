package generator;
import ClassDetailInfo.*;
import adapter.ClassDetailInfoDTO;
import org.junit.Before;
import org.junit.Test;
import shapes.ClassFormat;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ClassUnitGeneratorTest {
    private final String[]methods={"testMethod()","main()"},variables={"int tag","String name"};
    private final String startClassName ="main";
    private ClassUnitGenerator classUnitGenerator;
    private ClassDetailInfoDTO dto;
    @Before
    public void setUp(){
        ClassRelationship relation=new ClassRelationship("implement","main");
        MemberVariable variable=new MemberVariable("Public","int","test");
        MemberFunction method =new MemberFunction("Public","int","test()");
        ClassDetailInfo info=new ClassDetailInfo("main",variable,method,relation);
        dto =new ClassDetailInfoDTO(info);
        classUnitGenerator=new ClassUnitGenerator(dto);
    }
    @Test
    public void generateConcreteClassFormat() {
        classUnitGenerator.setClassAttributes(startClassName, Arrays.asList(methods),Arrays.asList(variables));
        ClassFormat startClass=classUnitGenerator.generateConcreteClassFormat();
        assertEquals(startClassName,startClass.getClassName());
        assertArrayEquals(methods,startClass.getMethods().toArray());
        assertArrayEquals(variables,startClass.getVariables().toArray());
    }

    @Test
    public void generateInterfaceClassFormat() {
        classUnitGenerator.setClassAttributes(dto);
        ClassFormat startClass=classUnitGenerator.generateInterfaceClassFormat();
        assertEquals(dto.getClassName(),startClass.getClassName());
        assertArrayEquals(dto.getMethods().toArray(),startClass.getMethods().toArray());
        assertArrayEquals(dto.getVariables().toArray(),startClass.getVariables().toArray());
    }


}