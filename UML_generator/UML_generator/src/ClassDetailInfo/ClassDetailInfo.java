package ClassDetailInfo;

import java.util.ArrayList;

public class ClassDetailInfo {
	private String _class_name;
	private ArrayList<ClassMemberAbstract> _MemberVariable=new ArrayList<ClassMemberAbstract>();
//	private MemberVariable[] _MemberVariable;
	private ArrayList<ClassMemberAbstract> _MemberFunction =new ArrayList<ClassMemberAbstract>();
	private ArrayList<ClassRelarionship> _ClassRelarionship = new ArrayList<ClassRelarionship>();

	public ClassDetailInfo() {}
	public ClassDetailInfo(String class_name, MemberVariable MemberVariable,MemberFunction MemberFunction, ClassRelarionship ClassRelarionship) {
		set_class_name(class_name);
		set_MemberVariable(MemberVariable);
		set_MemberFunction(MemberFunction);
		set_ClassRelarionship(ClassRelarionship);
	}
    
   
	public String get_class_name() {
		return _class_name;
	}
	public void set_class_name(String _class_name) {
		this._class_name = _class_name;
	}

	public ArrayList<ClassMemberAbstract> get_MemberVariable() {
		return _MemberVariable;
	}
	
	public void set_MemberVariable(ClassMemberAbstract variableInfo) {
		this._MemberVariable.add(variableInfo);
	}
	public ArrayList<ClassMemberAbstract> get_MemberFunction() {
		return _MemberFunction;
	}
	public void set_MemberFunction(ClassMemberAbstract MemberFunction) {
		this._MemberFunction.add(MemberFunction);
	}
	public ArrayList<ClassRelarionship> get_ClassRelarionship() {
		return _ClassRelarionship;
	}
	public void set_ClassRelarionship(ClassRelarionship ClassRelarionship) {
		this._ClassRelarionship.add( ClassRelarionship);
	}
	
	
	
	
	
}
