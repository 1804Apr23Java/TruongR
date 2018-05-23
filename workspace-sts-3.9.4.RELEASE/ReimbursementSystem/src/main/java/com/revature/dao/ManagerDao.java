package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface ManagerDao {
	/*
	public void viewManagerHomepage();
	public void resolveRequest(int managerId, int reimbursementID, boolean isApproved);
	public void viewPendingRequests();
	public void viewResolvedRequests();
	public void viewEmployeeRequests(int employeeId); */
	public List<Employee> getAllEmployees();
}
