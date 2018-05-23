package com.revature.dao;

import java.util.List;

import com.revature.beans.*;
import com.revature.exception.RequestAlreadyResolvedException;

public interface ManagerDao {
	public Request resolveRequest(int requestId, boolean isApproved, int managerId)
			throws RequestAlreadyResolvedException;

	public List<Request> getAllPendingRequests();

	public List<Request> getAllResolvedRequests();

	public List<Request> getAllEmployeeRequests(int employeeId);

	public List<Employee> getAllEmployees();
}
