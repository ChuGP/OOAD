package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ClassDetailInfo.ClassDetailInfo;
import ClassDetailInfo.ClassMemberAbstract;
import ClassDetailInfo.ClassRelarionship;
import ClassDetailInfo.MemberFunction;
import ClassDetailInfo.MemberVariable;

class ClassDetailInfoTest {
    private ClassDetailInfo classDetailInfo;
    private ClassMemberAbstract memberVariable;
    private ClassMemberAbstract memberFunction;
    private ClassMemberAbstract relation;
	@BeforeEach
	void setUp() throws Exception {
		memberVariable = new MemberVariable("Public", "int","GP");
		memberFunction = new MemberFunction("Public","int","draw(int x, int y)");
		relation = new ClassRelarionship("Extension","Draw");
		classDetailInfo = new ClassDetailInfo();
		classDetailInfo.setClassName("UML Generator");
		classDetailInfo.setMemberVariable(memberVariable);
		classDetailInfo.setMemberFunction(memberFunction);
		classDetailInfo.setClassRelarionship(relation);		
	}

	@Test
	void testGetClassName() {
		assertEquals("UML Generator", classDetailInfo.getClassName());
	}

	@Test
	void testClearClassName() {
		classDetailInfo.clearClassName();
		assertEquals("", classDetailInfo.getClassName());
	}
	

	@Test
	void testGetMemberVariable() {
		assertEquals(memberVariable, classDetailInfo.getMemberVariable().get(0));
	}

	@Test
	void testClearMemberVariable() {
		classDetailInfo.clearMemberVariable();
		assertTrue(classDetailInfo.getMemberVariable().isEmpty());
	}

	@Test
	void testGetMemberFunction() {
		assertEquals(memberFunction, classDetailInfo.getMemberFunction().get(0));
	}

	@Test
	void testClearMemberFunction() {
		classDetailInfo.clearMemberFunction();
		assertTrue(classDetailInfo.getMemberFunction().isEmpty());
	}

	@Test
	void testGetClassRelarionship() {
		assertEquals(relation, classDetailInfo.getClassRelarionship().get(0));
	}

	@Test
	void testClearClassRelarionship() {
		classDetailInfo.clearClassRelarionship();
		assertTrue(classDetailInfo.getClassRelarionship().isEmpty());
	}

}
