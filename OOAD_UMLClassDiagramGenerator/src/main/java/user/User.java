package user;

public class User {
	private int level;
	public User(int level) {
		setLevel(level);
	}
	
	public void setLevel(int level) {
		this.level=level;
	}
	
	public int getLevel(){
		return level;
	}

}
