package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserList {
	
	private static int totalListCreated;

	private int id;
	private String name;
	private Date creationDate;
	private boolean favourite;
	private String type;
	private String icon;
	private List<Item> itemList;
	
	public UserList() {
		// TODO Auto-generated constructor stub
	}

	public UserList(String name, String type, String icon) {
		super();
		
		totalListCreated++;
		
		this.id = totalListCreated;
		this.name = name;
		this.type = type;
		this.icon = icon;
		this.itemList = new ArrayList<Item>();
		this.creationDate = new Date();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public boolean addItem(Item item) {
		
		itemList.add(item);
		
		return true;
	}
	
	
	public static int getTotalListCreated() {
		return totalListCreated;
	}

	public static void setTotalListCreated(int totalListCreated) {
		UserList.totalListCreated = totalListCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void loadTotalListsCreated(int total) {
		
		totalListCreated = total;
	}

	
	
	@Override
	public String toString() {
		return "\nname: " + name + "\ncreationDate: " + creationDate + "\nfavourite: " + favourite + "\ntype: "
				+ type + "\nicon: " + icon + "\nitemList: " + itemList;
	}
	
	

}
