package auth;

import entity.User;
import persistance.ManageFile;

public class Login {

	public static User login(User user) {
		
		User userResponse;
		
		ManageFile mf = new ManageFile(user);
		
		User userFromJson = mf.loadJsonUserData();
		
		userResponse = checkPassword(user, userFromJson) ? userFromJson : null;
		
		return userResponse;
	}

	private static boolean checkPassword(User user, User userFromJson) {
		boolean response = (user.getPassword().equals(userFromJson.getPassword())) ? true: false;
		
		return response;
	}

}
