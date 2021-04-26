import java.util.List;
import java.util.Scanner;

import auth.Login;
import entity.UserList;
import entity.Item;
import entity.User;
import persistance.ManageFile;

public class Main {

	static Scanner scan = new Scanner(System.in);

	static User user;

	public static void main(String[] args) {
		
		Application.initApplication();
		
		login();
		

		ManageFile manageFile = new ManageFile(user);
		
		int option = -1;

		do {

			System.out.println("Bienvenido, selecciona una opción:");
			System.out.println("[1] Ver mis listas");
			System.out.println("[2] Añadir una lista");
			System.out.println("[3] Ver toda la info del usuario");
			System.out.println("[4] Guardar los datos");
			System.out.println("[5] Cargar los datos de un usuario");
			System.out.println("[6] Eliminar cuenta");
			System.out.println("[0] Salir");

			try {

				option = Integer.parseInt(scan.nextLine());

			} catch (Exception e) {
				System.err.println("Esa no es una opción valida");
			}

			switch (option) {
			case 1:
				// Ver listas
				List userLists = user.getLists();
				
				System.out.println(userLists.toString());
				break;
			case 2:
				// A�adir lista

				System.out.println("Como se llama la lista?");
				String listName = scan.nextLine();
				System.out.println("De que tipo es?");
				String listType = scan.nextLine();
				System.out.println("Ruta del icono");
				String listIcon = scan.nextLine();

				System.out.println("Cuantos items tiene la lista?");
				int numItems = Integer.parseInt(scan.nextLine());

				UserList itemList = new UserList(listName, listType, listIcon);

				for (int i = 0; i < numItems; i++) {
					System.out.println("Nombre: ");
					String itemName = scan.nextLine();
					System.out.println("Descripción: ");
					String itemDescription = scan.nextLine();

					itemList.addItem(new Item(itemName, itemDescription));
				}

				user.addList(itemList);

				System.out.println(user.toString());

				break;
			case 3:

				System.out.println(user.toString());

				break;

			case 4:

				manageFile.saveJsonUserData(user);

				break;
			case 5:

				user = manageFile.loadJsonUserData();
				System.out.println(user.toString());

				break;
			case 6: //Borrar CUENTA AL COMPLETO
				
				manageFile.BorrarFile(user);
				manageFile.BorrarPath(user);
				System.out.print("Hasta luego!");
				System.exit(0);
				break;

			}

		} while (option != 0);

		Application.finishApplication();
	}

	private static void login() {
		System.out.println("Introduce tu username: ");
		String username = scan.nextLine();
		System.out.println("Introduce tu password: ");
		String password = scan.nextLine();

		user = new User(username, password);
		
		user = Login.login(user);

	}

}
