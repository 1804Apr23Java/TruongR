package com.revature.domain;

import java.util.ArrayList;
import java.util.List;

public class BankClient {
	
	private String username;
	private String password;
	private List<Account> accountList;
	
	
	public BankClient(String username, String password, List<Account> accountList) {
		super();
		this.username = username;
		this.password = password;
		this.setAccountList(accountList);
	}

	public void resetUsername() {
		System.out.println("Please input new username");
		
	}
	
	public BankClient() {
		this.setAccountList(new ArrayList<Account>());
	}
	
	public void addAccount (int accountID, double balance) {
		this.setAccountList(new ArrayList<Account>());
		this.getAccountList().add(new Account(accountID, balance));
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Client " + username + " has the following accounts:\n");
		for (Account a: getAccountList()) {
			s.append(String.format("%d: Account %09d currently has $%.2f.\n", getAccountList().indexOf(a) + 1, 
					a.getAccountID(), a.getBalance()));
		}
		if (getAccountList().isEmpty())
			s.append("No accounts found.");
		
		return s.toString();
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}	
}
