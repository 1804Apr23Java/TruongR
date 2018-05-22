package com.revature.domain;

public class Manager extends Employee{
	
	@Override
	public String toString() {
		return super.toString() + "\nand Manager [managerId=" + managerId + "]";
	}

	private int managerId;
	
	public Manager(int employeeId, String username, String password, String firstName, String lastName, String address,
			String city, String state, String zip, String phone, String email, int managerId)
	{
		super(employeeId, username, password, firstName, lastName, address, city, state, zip, phone, email);
		this.setManagerId(managerId);
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
}
