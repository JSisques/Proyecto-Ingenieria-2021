package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	private static int totalUsersCreated;
	
	private int id;
	private String type;
	private String username;
	private String password;
	private String email;
	private String name;
	private String lastName;
	private Date creationDate;
	private List<UserList> lists;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String email) {
		super();
		
		totalUsersCreated++;
		
		this.id = totalUsersCreated;
		this.type = "user";
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = "";
		this.lastName = "";
		this.lists = new ArrayList<UserList>();
		this.creationDate = new Date();
		System.out.println(creationDate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<UserList> getLists() {
		return lists;
	}

	public void setLists(List<UserList> lists) {
		this.lists = lists;
	}
	
	public boolean addList(UserList list) {
		lists.add(list);
		return true;
	}
	
	public boolean removeList(UserList list) {
		lists.remove(list);
		return true;
	}
	
	
	
	public static int getTotalUsersCreated() {
		return totalUsersCreated;
	}

	public static void setTotalUsersCreated(int totalUsersCreated) {
		User.totalUsersCreated = totalUsersCreated;
	}

	public void loadTotalUsersCreated(int total) {
		
		totalUsersCreated = total;
	}

	@Override
	public String toString() {
		return 	"id: " + id + "\ntype: " + type + "\nusername: " + username + "\npassword: " + password + "\nemail: "
				+ email + "\nname: " + name + "\nlastName: " + lastName + "\ncreationDate: " + creationDate + "\nlists: "
				+ lists;
	}
	
	

}
