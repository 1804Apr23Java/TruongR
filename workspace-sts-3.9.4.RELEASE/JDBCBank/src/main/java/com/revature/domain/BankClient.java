package com.revature.domain;

import java.util.ArrayList;
import java.util.List;

public class BankClient implements User {
	

	
	public void resetUsername() {
		System.out.println("Please input new username");
		
	}
	
	protected List<Account> accountList;
	
	public BankClient() {
		this.accountList = new ArrayList<Account>();
	}
	
	public void addAccount (int accountID, double balance) {
		this.accountList = new ArrayList<Account>();
		this.accountList.add(new Account(accountID, balance));
	}

}
