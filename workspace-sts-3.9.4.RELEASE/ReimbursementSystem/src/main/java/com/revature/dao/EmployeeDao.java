package com.revature.dao;

import java.util.List;

import com.revature.beans.*;
import com.revature.util.EmployeeField;

public interface EmployeeDao {
	public Request submitRequest(int employeeId, double amount);
	public Request submitRequest(int employeeId, double amount, String imageLink);
	public List<Request> getPendingRequests(int employeeId);
	public List<Request> getResolvedRequests(int employeeId);
	public Employee getEmployee(int employeeId);
	public Employee updateEmployee(int employeeId, EmployeeField field, String updateValue);
}
