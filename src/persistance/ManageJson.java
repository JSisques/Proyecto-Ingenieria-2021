package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.User;

public class ManageJson {
	
	public static String convertToJson(Object object) {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		final String finalJson = prettyGson.toJson(object);
		System.out.println(finalJson);
		
		return finalJson;
	}
	
	public static User getUserFromJson(User user) {
		return new User();
	}

}
