package com.revature.domain;

public class Account {

	@Override
	public String toString() {
		return "Account [accountID=" + getAccountID() + ", balance=" + balance + "]";
	}

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

	public int getAccountID() {
		return accountID;
	}

}