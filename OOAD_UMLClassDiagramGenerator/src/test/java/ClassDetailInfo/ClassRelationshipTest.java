/**
 * 
 */
package ClassDetailInfo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author GP
 *
 */
class ClassRelationshipTest {

	/**
	 * @throws Exception
	 */
	private ClassMemberAbstract relation;
	@BeforeEach
	void setUp() throws Exception {
		relation = new ClassRelationship();
		relation.setReference("Extension");
		relation.setName("GP");
		
		
	}

	/**
	 * Test method for {@link ClassRelationship#reSet()}.
	 */
	@Test
	void testReSet() {
		relation.reSet();
		assertEquals("", relation.getReference());
		assertEquals("", relation.getName());
		
	}

	/**
	 * Test method for {@link ClassRelationship#getReference()}.
	 */
	@Test
	void testGetReference() {
		assertEquals("Extension", relation.getReference());
	}

	/**
	 * Test method for {@link ClassRelationship#getName()}.
	 */
	@Test
	void testGetName() {
		assertEquals("GP", relation.getName());
	}

}
