package ClassDetailInfo;

public class MemberFunction extends ClassMemberAbstract{
	private String _reference, _functuonType, _functuonName;
	public MemberFunction() {}
	public MemberFunction(String reference, String functuonType, String functuonName) 
	{
		_reference = reference;
		_functuonType = functuonType;
		_functuonName = functuonName;
	}
	
	@Override
	public String get_Type() {
		return _functuonType;
	}
	@Override
	public void set_Type(String _functuonType) {
		this._functuonType = _functuonType;
	}
	@Override
	public String get_Reference() {
		return _reference;
	}
	@Override
	public void set_Reference(String _reference) {
		this._reference = _reference;
	}
	@Override
	public String get_Name() {
		return _functuonName;
	}
	@Override
	public void set_Name(String _functuonName) {
		this._functuonName = _functuonName;
	}

}
