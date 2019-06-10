package ClassDetailInfo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberVariableTest {
	private ClassMemberAbstract memberVariable;
	@BeforeEach
	void setUp() throws Exception {
		memberVariable = new MemberVariable();
		memberVariable.setReference("Public");
		memberVariable.setType("int");
		memberVariable.setName("GP");
	}

	@Test
	void testReSet() {
		memberVariable.reSet();
		assertEquals("", memberVariable.getReference());
		assertEquals("", memberVariable.getType());
		assertEquals("", memberVariable.getName());
	}

	@Test
	void testGetReference() {
		assertEquals("Public", memberVariable.getReference());
	}

	@Test
	void testGetType() {
		assertEquals("int", memberVariable.getType());
	}

	@Test
	void testGetName() {
		assertEquals("GP", memberVariable.getName());
	}

}
