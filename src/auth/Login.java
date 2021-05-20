package auth;

import entity.User;
import persistance.ManageFile;

public class Login {

	public static User login(User user) {
		
		User userResponse;
		
		ManageFile mf = new ManageFile(user);
		
		try {
			User userFromJson = mf.loadJsonUserData();
			userResponse = checkPassword(user, userFromJson) ? userFromJson : null;
		} catch (Exception e) {
			userResponse = user;
		}
		
		return userResponse;
	}

	private static boolean checkPassword(User user, User userFromJson) {
		boolean response = (user.getPassword().equals(userFromJson.getPassword())) ? true: false;
		
		return response;
	}
	
	public static User auxLogin(String username, String password) {
		
		return new User();
		
	}
	
	public static User singUp(String username, String password, String email) {
		User u = new User(username, password, email	);
		
		return u;
		
	}

}
