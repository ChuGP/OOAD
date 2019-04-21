package ClassDetailInfo;

import java.util.ArrayList;

public class ClassDetailInfo {
	private String _class_name;
	private ArrayList<ClassMemberAbstract> _MemberVariable=new ArrayList<ClassMemberAbstract>();
	private ArrayList<ClassMemberAbstract> _MemberFunction =new ArrayList<ClassMemberAbstract>();
	private ArrayList<ClassMemberAbstract> _ClassRelarionship = new ArrayList<ClassMemberAbstract>();

	public ClassDetailInfo() {}
	public ClassDetailInfo(String class_name, MemberVariable MemberVariable,MemberFunction MemberFunction, ClassMemberAbstract ClassRelarionship) {
		setClassName(class_name);
		setMemberVariable(MemberVariable);
		setMemberFunction(MemberFunction);
		setClassRelarionship(ClassRelarionship);
	}
    
   
	public String getClassName() {
		return _class_name;
	}
	
	public void setClassName(String _class_name) {
		this._class_name = _class_name;
	}
	
	public void clearClassName()
	{
		this._class_name = "";
	}

	public ArrayList<ClassMemberAbstract> getMemberVariable() {
		return _MemberVariable;
	}
	
	public void setMemberVariable(ClassMemberAbstract variableInfo) {
		this._MemberVariable.add(variableInfo);
	}
	
	public void clearMemberVariable() {
		this._MemberVariable.clear();
	}
	
	public ArrayList<ClassMemberAbstract> getMemberFunction() {
		return _MemberFunction;
	}
	
	public void setMemberFunction(ClassMemberAbstract MemberFunction) {
		this._MemberFunction.add(MemberFunction);
	}
	
	public void clearMemberFunction() {
		this._MemberFunction.clear();
	}
	
	public ArrayList<ClassMemberAbstract> getClassRelarionship() {
		return _ClassRelarionship;
	}
	
	public void setClassRelarionship(ClassMemberAbstract ClassRelarionship) {
		this._ClassRelarionship.add( ClassRelarionship);
	}
	
	public void clearClassRelarionship() {
		this._ClassRelarionship.clear();
	}
	
	
	
	
	
}
