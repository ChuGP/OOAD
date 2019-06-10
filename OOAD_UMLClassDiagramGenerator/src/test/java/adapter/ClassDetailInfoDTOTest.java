package adapter;

import ClassDetailInfo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassDetailInfoDTOTest {
    ClassDetailInfoDTO dto;
    @Before
    public void setUp(){
        ClassRelationship relation=new ClassRelationship("implement","main");
        MemberVariable variable=new MemberVariable("Public","int","test");
        MemberFunction method =new MemberFunction("Public","int","test()");

         ClassDetailInfo info=new ClassDetailInfo("main",variable,method,relation);
         dto=new ClassDetailInfoDTO(info);
    }
    @Test
    public void getMethods() {
        assertEquals(" + test() : int",dto.getMethods().get(0));
    }

    @Test
    public void getVariables() {
        assertEquals(" + test : int",dto.getVariables().get(0));
    }

    @Test
    public void getClassName() {
        assertEquals("main",dto.getClassName());
    }
}