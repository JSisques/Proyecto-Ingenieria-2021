import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import auth.Login;
import entity.UserList;
import entity.Email;
import entity.Item;
import entity.User;
import persistance.ManageFile;

public class Main {

	static Scanner scan = new Scanner(System.in);

	static User user;

	public static void main(String[] args) {
		
		int option = -1;

		Application.initApplication();

		System.out.println("Bienvenido, selecciona una opción:");
		System.out.println("[1] => Sign in");
		System.out.println("[2] => Sign up");
		System.out.println("[0] => Salir");
		option = Integer.parseInt(scan.nextLine());
		
		switch (option) {
		case 1:
			
			while (!login()) {}
			
			break;
			
		case 2:
			
			System.out.println("Introduce tu username: ");
			String username = scan.nextLine();
			System.out.println("Introduce tu password: ");
			String password = scan.nextLine();
			System.out.println("Introduce tu email: ");
			String email = scan.nextLine();
			
			user = Login.singUp(username, password, email);
			
			break;
			
		case 0:
			System.exit(0);
		}
		
		
		ManageFile manageFile = new ManageFile(user);

		do {

			System.out.println("Bienvenido, selecciona una opción:");
			System.out.println("[1]  => 	Ver mis listas");
			System.out.println("[2]  => 	Añadir una lista");
			System.out.println("[3]  => 	Ver toda la info del usuario");
			System.out.println("[4]  => 	Guardar los datos");
			System.out.println("[5]  => 	Cargar los datos de un usuario");
			System.out.println("[6]  =>		Eliminar cuenta");
			System.out.println("[7]  => 	Enviar correo electrónico");
			System.out.println("[8]  =>		Añadir un item a una lista");
			System.out.println("[9]  => 	Borrar una list");
			System.out.println("[10] => 	Cambiar información personal");
			System.out.println("[11] => 	Borrar un item de una lista");
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
			case 6: // Borrar CUENTA AL COMPLETO

				manageFile.borrarFile(user);
				manageFile.borrarPath(user);
				System.out.print("Hasta luego!");
				System.exit(0);
				break;
			case 7:

				Email email = new Email();
				email.sendEmail(user);
				break;
			case 8:

				System.out.println("Introduce el nombre de la lista: ");
				String list = scan.nextLine();
				System.out.println("Introduce el nombre del item: ");
				String itemName = scan.nextLine();
				System.out.println("Introduce la descripcion del item: ");
				String itemDescription = scan.nextLine();

				Item i = new Item(itemName, itemDescription);

				UserList userList = new UserList();

				for (UserList currentList : user.getLists()) {
					if (list.equals(currentList.getName())) {
						userList = currentList;
					}
				}

				userList.addItem(i);

				break;

			case 9:

				System.out.println("Introduce el nombre de la lista: ");
				String l = scan.nextLine();

				UserList ul = new UserList();

				for (UserList currentList : user.getLists()) {
					if (l.equals(currentList.getName())) {
						ul = currentList;
					}
				}

				user.removeList(ul);

				break;
			
			case 10:

				System.out.println("Introduce el correo electronico");
				String changeEmail = scan.nextLine();
				System.out.println("Introduce el nombre");
				String changeName = scan.nextLine();
				System.out.println("Introduce el apellido");
				String changeLastName = scan.nextLine();
				
				user.setEmail(changeEmail);
				user.setName(changeName);
				user.setLastName(changeLastName);
				
				break;
				
			case 11:
				
				System.out.println("Introduce el nombre de la lista: ");
				l = scan.nextLine();
				
				System.out.println("Introduce el nombre del item: ");
				itemName = scan.nextLine();
				
				userLists = user.getLists();
				
				
				
				break;


			}

		} while (option != 0);

		Application.finishApplication();
	}

	private static boolean login() {
		boolean response = false;
		
		System.out.println("Introduce tu username: ");
		String username = scan.nextLine();
		System.out.println("Introduce tu password: ");
		String password = scan.nextLine();
		System.out.println("Introduce tu email: ");
		String email = scan.nextLine();

		user = new User(username, password, email);

		user = Login.login(user);
		
		if (user == null) {
			System.err.println("Error, usuario o contraseña incorrectos");
		} else {
			response = true;
		}
		
		return response;

	}

}
