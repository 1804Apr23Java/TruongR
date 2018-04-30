package com.revature.banking;



public class Account {
	
	private final int id;
	private double balance;

	public Account(int id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}