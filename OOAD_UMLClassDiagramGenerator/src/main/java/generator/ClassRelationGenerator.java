package generator;

import diagrams.RelationType;
import shapes.ClassFormat;
import shapes.Relation;

public class ClassRelationGenerator {

	public ClassRelationGenerator() {
		
	}
	
	public Relation generateRelation(ClassFormat startClass , ClassFormat endClass , RelationType relationType) {
		Relation relation=new Relation(startClass,endClass,relationType);
		return relation;
	}
	
}
