package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface ManagerDao {
	/*
	public void viewManagerHomepage();
	public void resolveRequest(int reimbursementRequestID, boolean isApproved);
	public void viewPendingRequests();
	public void viewResolvedRequests();
	public void viewEmployeeRequests(Employee e); */
	public List<Employee> getAllEmployees();
}
