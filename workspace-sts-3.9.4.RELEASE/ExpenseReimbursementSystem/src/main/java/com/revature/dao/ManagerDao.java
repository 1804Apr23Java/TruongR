package com.revature.dao;

import com.revature.domain.*;

public interface ManagerDao {
	public Manager login(String managerID, String password);
	public void viewManagerHomepage();
	public void logout();
	public void handleReimbursementRequests(int reimbursementRequestID, boolean isApproved);
	public void viewAllPendingRequests();
	public void viewReceipt(int reimbursementRequestID);
	public void viewAllEmployees();
	public void viewAllReinbursementRequests(Employee e);
}