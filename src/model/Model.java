package model;

import entity.Item;
import entity.User;
import entity.UserList;
import persistance.ManageFile;

public class Model {

	private User user;

	private ManageFile manageFile = new ManageFile(user);
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User addList(String listName, String listType, String listIcon) {
		UserList itemList = new UserList(listName, listType, listIcon);

		user.addList(itemList);

		System.out.println(user.toString());
		return user;
	}

	public boolean save() {
		boolean response = false;

		response = manageFile.saveJsonUserData(user);
		
		return response;
	}
	
	public User load() {
		
		return manageFile.loadJsonUserData();
	}

}
