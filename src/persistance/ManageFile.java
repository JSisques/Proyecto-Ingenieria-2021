package persistance;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Component;
import entity.Item;
import entity.User;

public class ManageFile {

	private File file;

	private String path; // proyecto/users/nombreUsuario/data.json
	private String fileName;

	Gson gson;
	
	public ManageFile(String path) {
		file = new File(path);
		comprobarSiExiste(file);
	}

	public ManageFile(User user) {

		gson = new Gson();

		this.path = "./users/" + user.getUsername() + "/";
		System.out.println(path);
		fileName = "data.json";
		file = new File(this.path, fileName);

		comprobarSiExiste(file);
	}

	private boolean comprobarSiExiste(File file) {

		boolean respuesta = file.exists() ? true : createFile(file);
		return respuesta;

	}

	public boolean createFile(File file) {
		System.out.println("Creando archivo");
		boolean response = false;

		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			System.out.println("Archivo creado");
			response = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	/*
	 * public boolean deleteUser() {
	 * 
	 * }
	 * 
	 * public boolean updateUser() {
	 * 
	 * }
	 * 
	 */
	public boolean saveJsonUserData(User user) {
		boolean response = false;
		
		Gson auxGson = new GsonBuilder()
				  .setPrettyPrinting()
				  .serializeNulls()
				  .create();

		try {
			Writer fr = new FileWriter(file);
			auxGson.toJson(user, fr);
	        fr.flush(); //flush data to file   <---
	        fr.close(); //close write 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;

	}

	public User loadJsonUserData() {
		boolean response = false;
		User user = null;

		try {
		    Reader reader = Files.newBufferedReader(Paths.get(this.path + fileName));
		    user = gson.fromJson(reader,User.class);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}
	
	public void loadAppData() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(file));
			User.setTotalUsersCreated(Integer.parseInt(properties.getProperty("totalUsers")));
			Component.setTotalListCreated(Integer.parseInt(properties.getProperty("totalLists")));
			Item.setTotalItemsCreated(Integer.parseInt(properties.getProperty("totalItems")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveAppData() {
		Properties properties = new Properties();
		try {
			properties.setProperty("totalUsers", String.valueOf(User.getTotalUsersCreated()));
			properties.setProperty("totalLists", String.valueOf(Component.getTotalListCreated()));
			properties.setProperty("totalItems", String.valueOf(Item.getTotalItemsCreated()));
			properties.store(new FileWriter(file), "App data");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void restoreAppData() {
		Properties properties = new Properties();
		try {
			properties.setProperty("totalUsers", String.valueOf(0));
			properties.setProperty("totalLists", String.valueOf(0));
			properties.setProperty("totalItems", String.valueOf(0));
			properties.store(new FileWriter(file), "App data");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		loadAppData();
	}
}
