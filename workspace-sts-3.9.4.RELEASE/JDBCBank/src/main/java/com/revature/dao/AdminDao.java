package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface AdminDao {
	
	public User getUser(int userId);
	public List<User> getAllUsers();
	public void updateUserUsername(int userId, String newUsername);
	public void updateUserPassword(int userId, String newPassword);
	public void createUser(int userId, String username, String password);
	public void deleteUser(int userId);
	
}
