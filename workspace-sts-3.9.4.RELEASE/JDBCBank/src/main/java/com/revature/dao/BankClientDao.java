package com.revature.dao;

import com.revature.domain.*;

public interface BankClientDao {
	
	public Account createAccount(int bankClientID);
	public void viewAccounts(int bankClientID);
	public void deleteAccount(int bankClientID, int accountID) throws MoneyInAccountException;
	public void deposit(int bankClientID, double amt, int accountID);
	public void withdraw(int bankClientID, double amt, int accountID) throws OverdraftException;
	
	public void getUserHistory(int bankClientId);
}
