package com.revature.beans;

import java.sql.Timestamp;

public class Request {

	private int requestId;
	private int employeeId;
	private double amount;
	private Timestamp requestTime;
	private String imageLink;
	
	private int status;
	//0 = pending, 1 = accepted, 2 = rejected
	
	private int managerId;
	//-1 = unassigned
	
	public Request(int requestId, int employeeId, double amount, Timestamp requestTime, String imageLink, int status, int managerId) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = imageLink;
		this.status = status;
		this.managerId = managerId;
	}
	
	public Request(int requestId, int employeeId, double amount, Timestamp requestTime, String imageLink) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = imageLink;
		this.status = 0;
		this.managerId = -1;
	}
	
	public Request(int requestId, int employeeId, double amount, Timestamp requestTime) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = null;
		this.status = 0;
		this.managerId = -1;
	}
	
	public Request(int employeeId, double amount, Timestamp requestTime, String imageLink, int status, int managerId) {
		super();
		this.requestId = -1;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = imageLink;
		this.status = status;
		this.managerId = managerId;
	}

	public Request(int employeeId, double amount, Timestamp requestTime, int status, int managerId) {
		super();
		this.requestId = -1;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = null;
		this.status = status;
		this.managerId = managerId;
	}
	
	public Request(int employeeId, double amount, Timestamp requestTime) {
		super();
		this.requestId = -1;
		this.employeeId = employeeId;
		this.amount = amount;
		this.setRequestTime(requestTime);
		this.imageLink = null;
		this.status = 0;
		this.managerId = -1;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", employeeId=" + employeeId + ", amount=" + amount + ", imageLink="
				+ imageLink + ", status=" + status + ", managerId=" + managerId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + employeeId;
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + managerId;
		result = prime * result + requestId;
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (imageLink == null) {
			if (other.imageLink != null)
				return false;
		} else if (!imageLink.equals(other.imageLink))
			return false;
		if (managerId != other.managerId)
			return false;
		if (requestId != other.requestId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
}
