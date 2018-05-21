package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface ManagerDao {
	/*
	public Manager login(String managerID, String password);
	public void viewManagerHomepage();
	public void logout();
	public void handleReimbursementRequests(int reimbursementRequestID, boolean isApproved);
	public void viewAllPendingRequests();
	public void viewReceipt(int reimbursementRequestID);
	public void viewEmployeeRequests(Employee e); */ 
	public Employee addEmployee(String username, String password, String firstName, String lastName, String address,
			String city, String state, String zip, String phone, String email);
	public Employee getEmployee (String username);
	public Employee getEmployee (int id);
	public List<Employee> getAllEmployees();
}
