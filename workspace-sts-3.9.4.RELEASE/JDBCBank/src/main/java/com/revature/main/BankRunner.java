package com.revature.main;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.*;
import com.revature.domain.*;

public class BankRunner {

	public static final int MAX_TRIES = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankClientDao bcDao = new BankClientDaoPLSQL();
		AdminDao aDao = new AdminDaoPLSQL();
	
		aDao.deleteBankClient(12);
		
		
		/* try {
			aDao.updateBankClientUsername(3, "Jordan1231");
		} catch (UsernameAlreadyUsedException e) {
			System.out.println("Error: Username Jordan1232 already used.");
			e.printStackTrace();
		} */
		
		/* try {
			aDao.createBankClient("butts2", "butts2");
		} catch (UsernameAlreadyUsedException e) {
			System.out.println("Error: Username butts2 already used.");
			e.printStackTrace();
		} */
		/*
		try {
			aDao.createBankClient("butts2", "butts2");
		} catch (UsernameAlreadyUsedException e) {
			System.out.println("Error: Username butts already used.");
			e.printStackTrace();
		} */
		
		//aDao.updateBankClientPassword(1, "AMrvel2017!?");
		
		
		//System.out.println(aDao.getBankClient(1));
		
		
//		try {
//			bcDao.deleteAccount(19);
//		} catch (MoneyInAccountException e) {
//			System.out.println("Error: account still has money left.");
//		}
//		
//		try {
//			bcDao.deleteAccount(16);
//		} catch (MoneyInAccountException e) {
//			System.out.println("Error: account still has money left.");
//		}

		// try (Scanner s = new Scanner(System.in)) {
		//
		// boolean credentialsValid = false;
		//
		// System.out.println("Welcome to JDBC Bank. Select an option.");
		// System.out.println("1.\tLog into account.");
		// System.out.println("2.\tCreate account.");
		// System.out.println("3.\tExit.");
		//
		// String option = s.next();
		//
		// try {
		//
		// int i = Integer.parseInt(option);
		//
		// switch (i) {
		//
		// case 1:
		// // prompt for username and password
		// logIn(s, dao);
		// break;
		// case 2:
		// // prompt for username and password
		// registerUser(s, dao);
		// break;
		// case 3:
		// System.out.println("Goodbye.");
		// System.exit(0);
		// break;
		// default:
		// throw new NumberFormatException();
		//
		// }
		//
		// } catch (NumberFormatException e) {
		// System.out.println("Error: input not an option.");
		// System.out.println("Goodbye.");
		// System.exit(0);
		// }
		//
		// // check if username and password are valid
		//
		// } catch (Exception e) {
		//
		// }

	}

	// private static void logIn(Scanner s, BankDao dao) {
	// String username, password;
	// System.out.print("Input username: ");
	// username = s.next();
	// System.out.print("Input password: ");
	// password = s.next();
	// try {
	// dao.logIn(username, password);
	// } catch (IncorrectUsernameException e) {
	// System.out.println("Error: Username not found in database.");
	// } catch (IncorrectPasswordException e) {
	// System.out.println("Error: Password incorrect.");
	// }
	// }
	//
	// private static void registerUser(Scanner s, BankDao dao) {
	// String username, password;
	// System.out.print("Input new username: ");
	// username = s.next();
	// System.out.print("Input new password: ");
	// password = s.next();
	// try {
	// dao.registerNewUser(username, password);
	// } catch (UsernameAlreadyUsedException e) {
	// System.out.println("Error: Username already exists in database.");
	// }
	//
	// }
}
