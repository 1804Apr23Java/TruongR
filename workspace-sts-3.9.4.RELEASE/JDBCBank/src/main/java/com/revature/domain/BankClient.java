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
		this.accountList = accountList;
	}

	public void resetUsername() {
		System.out.println("Please input new username");
		
	}
	
	public BankClient() {
		this.accountList = new ArrayList<Account>();
	}
	
	public void addAccount (int accountID, double balance) {
		this.accountList = new ArrayList<Account>();
		this.accountList.add(new Account(accountID, balance));
	}

	@Override
	public String toString() {
		return "BankClient [username=" + username + ", password=" + password + ", accountList=" + accountList + "]";
	}	
}
