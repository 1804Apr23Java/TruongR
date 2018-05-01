package com.revature.banking;

public class Account {

	private final int accountID;
	private double balance;

	public Account(int id, double balance) {
		super();
		this.accountID = id;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}