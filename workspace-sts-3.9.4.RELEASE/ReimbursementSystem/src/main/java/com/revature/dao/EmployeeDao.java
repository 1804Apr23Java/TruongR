package com.revature.dao;

import com.revature.domain.*;

public interface EmployeeDao {
	public Employee login(String username, String password);
	public void viewEmployeeHomepage();
	public void logout();
	public void submitReimbursmentRequest();
	public void uploadReceipt(Employee e, int reimbursementRequestID);
	public void viewPendingReimbursementRequests(Employee e);
	public void viewResolvedReimbursementRequests(Employee e);
	public void viewInformation(Employee e);
	public void updateInformation(Employee e, String field, String updateValue);
}
