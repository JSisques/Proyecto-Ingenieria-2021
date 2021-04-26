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

import entity.UserList;
import entity.Item;
import entity.User;

public class ManageFile {

	private File file;

	private String path; // ./users/nombreUsuario/data.json
	private String fileName;

	Gson gson;
	
	public ManageFile(String path) {
		file = new File(path);
		comprobarSiExiste(file);
	}

	public ManageFile(User user) {

		gson = new Gson();

		fileName = "data.json";
		path = "./users/" + user.getUsername() + "/" + fileName;
		System.out.println(path);
		file = new File(path);

		comprobarSiExiste(file);
	}

	/**
	 * Comprobar si existe un fichero
	 * @param file El fichero que queremos comprobar
	 * @return True siempre ya que en el caso de no existir se crea
	 */
	private boolean comprobarSiExiste(File file) {

		boolean respuesta = file.exists() ? true : createFile(file);
		return respuesta;

	}
	
	
	/**
	 * Crea un fichero en una ruta determinada
	 * @param file El fichero que queremos crear
	 * @return True si el archivo se ha creado, false en caso contrario
	 */
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
		    Reader reader = Files.newBufferedReader(Paths.get(path));
		    user = gson.fromJson(reader, User.class);
		    System.out.println(user.toString());
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
			UserList.setTotalListCreated(Integer.parseInt(properties.getProperty("totalLists")));
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
			properties.setProperty("totalLists", String.valueOf(UserList.getTotalListCreated()));
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
	
	
	public void borrarPath(User user) { //elimino la carpeta pero antes debemos eliminar el archivo
		this.path = "./users/" + user.getUsername() + "/";
		System.out.println(path);
		
		String ficheroruta = file.getParent();
		System.out.println(ficheroruta);
		File ficheroeliminar = new File(ficheroruta);
		
		if (ficheroeliminar.delete()) { 
			System.out.println("Deleted the folder: " + ficheroeliminar.getName());
		} else {
			System.out.println("Failed to delete the folder.");
		}   
	}

	public void borrarFile(User user) { //elimino un archivo dentro de la carpeta del usuario
	 
		comprobarSiExiste(file);
		
	    if (file.delete()) { 
	      System.out.println("Deleted the file: " + file.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    } 
	} 
}
