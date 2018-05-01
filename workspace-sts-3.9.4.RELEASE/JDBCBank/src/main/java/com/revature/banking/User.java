package com.revature.banking;

import java.util.ArrayList;
import java.util.List;

public class User {
	protected List<Account> accountList;
	
	public User() {
		this.accountList = new ArrayList<Account>();
	}
	
	public User (int accountID, double balance) {
		this.accountList = new ArrayList<Account>();
		this.accountList.add(new Account(accountID, balance));
	}
}
