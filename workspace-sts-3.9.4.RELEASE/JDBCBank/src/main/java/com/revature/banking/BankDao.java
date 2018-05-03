package com.revature.banking;

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