package generator;

import diagrams.RelationType;
import org.junit.Before;
import org.junit.Test;
import shapes.ClassFormat;
import shapes.Relation;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ClassRelationGeneratorTest {
    private final String[]methods={"testMethod()","main()"},variables={"int tag","String name"};
    private final String startClassName ="main",endClassName="UMLClassDiagram";
    private ClassFormat startClass,endClass;
    @Before
    public void setUp(){
        ClassUnitGenerator classUnitGenerator=new ClassUnitGenerator();
        classUnitGenerator.setClassAttributes(startClassName, Arrays.asList(methods),Arrays.asList(variables));
        startClass=classUnitGenerator.generateConcreteClassFormat();
        classUnitGenerator.setClassAttributes(endClassName, Arrays.asList(methods),Arrays.asList(variables));
        endClass=classUnitGenerator.generateConcreteClassFormat();
    }
    @Test
    public void generateRelation() {
        ClassRelationGenerator classRelationGenerator=new ClassRelationGenerator();
        Relation actualRelation=classRelationGenerator.generateRelation(startClass,endClass, RelationType.Association);
        assertSame(startClass,actualRelation.getStartClass());
        assertSame(endClass,actualRelation.getEndClass());
        assertEquals(RelationType.Association,actualRelation.getRelationType());
    }
}