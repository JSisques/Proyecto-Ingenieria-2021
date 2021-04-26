package auth;

import entity.User;
import persistance.ManageFile;

public class Login {

	public static User login(User user) {
		
		ManageFile mf = new ManageFile(user);
		
		user = mf.loadJsonUserData();
		
		//System.out.println(user.toString());
		
		
		return user;
	}

}
