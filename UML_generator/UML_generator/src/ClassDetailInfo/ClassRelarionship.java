package ClassDetailInfo;

public class ClassRelarionship {
    private String _realtionship, _className;
    public ClassRelarionship() {}
    public ClassRelarionship(String realtionship, String className) 
    {
    	set_realtionship(realtionship);
    	set_className(className) ;
    }
	public String get_className() {
		return _className;
	}
	public void set_className(String _className) {
		this._className = _className;
	}
	public String get_realtionship() {
		return _realtionship;
	}
	public void set_realtionship(String _realtionship) {
		this._realtionship = _realtionship;
	}
}
