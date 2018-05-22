package com.revature.dao;

import com.revature.domain.*;

public interface EmployeeDao {
	public Employee login(String username, String password);
	public void viewEmployeeHomepage();
	public void logout();
	public void submitRequest(int employeeId, double amount);
	public void submitRequest(int employeeId, double amount, String imagelink);
	public void viewPendingRequests(int employeeId);
	public void viewResolvedRequests(int employeeId);
	public Employee getEmployee(int employeeId);
	public void updateEmployee(int employeeId, int field, String updateValue);
}
