package com.revature.model;

public class FoodComment {

	public FoodComment(int userId, String username, String comment) {
		super();
		this.userId = userId;
		this.username = username;
		this.comment = comment;
	}
	
	int userId;
	
	String username;
	
	String comment;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
