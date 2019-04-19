package ClassDetailInfo;

public class MemberVariable extends ClassMemberAbstract{
	private String _reference, _variableType, _variableName;
	public MemberVariable() {}
	public MemberVariable(String reference, String variableType, String variableName) 
	{
		_reference = reference;
		_variableType = variableType;
		_variableName = variableName;
	}
	
	@Override
	public String get_Reference() {
		return _reference;
	}
	
	@Override
	public void set_Reference(String reference) {
		this._reference = reference;
	}
	
	@Override
	public String get_Type() {
		return _variableType;
	}
	
	@Override
	public void set_Type(String variableType) {
		this._variableType = variableType;
	}
	
	@Override
	public String get_Name() {
		return _variableName;
	}
	
	@Override
	public void set_Name(String variableName) {
		this._variableName = variableName;
	}
	
	
	
	
	
	
	
	
	
	

}
