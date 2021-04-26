import persistance.ManageFile;

public class Application {
	
	private static String appFile = "./app/app.properties";
	private static ManageFile manageFile = new ManageFile(appFile);
	
	public static void initApplication() {
		
		loadData();
		
	}
	
	public static void finishApplication() {
		
		saveData();
		
	}

	private static void loadData() {
		manageFile.loadAppData();
	}
	
	private static void saveData() {
		
		manageFile.saveAppData();
	}

}
