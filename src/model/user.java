
package model;

import helper.DBConnection;

public class user {
	
	private int id;
	String name,password,UserID,type;
	
	DBConnection conn = new DBConnection();
	
	public user(int id, String name, String password, String userID, String type) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.UserID = userID;
		this.type = type;
	}
	
	public user () {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}


