package com.revature.dao;

import com.revature.domain.*;

public interface BankClientDao {

	public Account createAccount(int bankClientId, double startingValue);
	public Account createAccount(int bankClientId);
	public void viewAccounts(int bankClientId);
	public void deleteAccount(int accountId) throws MoneyInAccountException;
	public void deposit(double amt, int accountId);
	public void withdraw(double amt, int accountId) throws OverdraftException;
}
