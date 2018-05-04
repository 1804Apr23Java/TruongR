package com.revature.dao;

import com.revature.domain.IncorrectPasswordException;
import com.revature.domain.IncorrectUsernameException;
import com.revature.domain.OverdraftException;
import com.revature.domain.UsernameAlreadyUsedException;

public interface BankDao {

	void registerNewUser(String username, String password) throws UsernameAlreadyUsedException;
	void logIn(String username, String password) throws IncorrectUsernameException, IncorrectPasswordException;
	void viewAccount();
	void createAccount();
	void deleteAccount();
	void addMoney();
	void withdrawMoney() throws OverdraftException;
	void logOut();
	
}