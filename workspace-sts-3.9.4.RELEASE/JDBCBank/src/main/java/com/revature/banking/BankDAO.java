package com.revature.banking;

public interface BankDAO {

	void registerNewAccount() throws UsernameAlreadyUsedException;
	void logIn() throws IncorrectUsernameException, IncorrectPasswordException;
	void viewAccount();
	void createAccount() throws UsernameAlreadyUsedException;
	void deleteAccount();
	void addMoney();
	void withdrawMoney() throws OverdraftException;
	void logOut();
	
}