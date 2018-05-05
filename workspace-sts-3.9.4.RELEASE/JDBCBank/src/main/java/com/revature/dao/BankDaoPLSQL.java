package com.revature.dao;

import java.sql.*;

import com.revature.domain.IncorrectPasswordException;
import com.revature.domain.IncorrectUsernameException;
import com.revature.domain.OverdraftException;
import com.revature.domain.UsernameAlreadyUsedException;

public class BankDaoPLSQL implements BankDao{

	public BankDaoPLSQL() {
		
	}
	
	@Override
	public void registerNewUser(String username, String password) throws UsernameAlreadyUsedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logIn(String username, String password) throws IncorrectUsernameException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMoney() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawMoney() throws OverdraftException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		
	}

}
