package user;

public class UserManager {
	private User user;

	public UserManager() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void idenfityUserLevel(User user){
		setUser(user);
	}
	
	public void displayFunction() {
		
	}
}
