package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ClassDetailInfo.ClassMemberAbstract;
import ClassDetailInfo.MemberFunction;

class MemberFunctionTest {
    
	private ClassMemberAbstract memberFunction;
	@BeforeEach
	void setUp() throws Exception {
		memberFunction = new MemberFunction();
		memberFunction.setReference("Public");
		memberFunction.setType("int");
		memberFunction.setName("draw(int x, int y)");
		
	}

	@Test
	void testReSet() {
		memberFunction.reSet();
		assertEquals("", memberFunction.getReference());
		assertEquals("", memberFunction.getType());
		assertEquals("", memberFunction.getName());
	}

	@Test
	void testGetReference() {
		assertEquals("Public", memberFunction.getReference());
	}

	@Test
	void testGetType() {
		assertEquals("int", memberFunction.getType());
	}

	@Test
	void testGetName() {
		assertEquals("draw(int x, int y)", memberFunction.getName());
	}

}
