package ClassDetailInfo;

public class ClassRelarionship extends ClassMemberAbstract {
    private String _realtionship, _className;
    public ClassRelarionship() {}
    public ClassRelarionship(String realtionship, String className) 
    {
    	_realtionship = realtionship;
    	_className = className;
    }
    @Override
	public String getName() {
		return _className;
	}
    @Override
	public void setName(String _className) {
		this._className = _className;
	}
    @Override
	public String getReference() {
		return _realtionship;
	}
    @Override
	public void setReference(String _realtionship) {
		this._realtionship = _realtionship;
	}
    @Override
    public void reSet()
    {
    	_realtionship = "";
    	_className = "";
    }
    
}
