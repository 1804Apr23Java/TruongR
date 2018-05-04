package com.revature.banking;

import java.util.Scanner;

public class BankRunner {

	public static final int MAX_TRIES = 5;

	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankDaoPLSQLImpl dao = new BankDaoPLSQLImpl();

		try (Scanner s = new Scanner(System.in)) {

			boolean credentialsValid = false;

			System.out.println("Welcome to JDBC Bank. Select an option.");
			System.out.println("1.\tLog into account.");
			System.out.println("2.\tCreate account.");
			System.out.println("3.\tExit.");

			String option = s.next();

			try {

				int i = Integer.parseInt(option);

				switch (i) {

				case 1:
					// prompt for username and password
					attemptLogIn(s, dao);
					break;
				case 2:
					// prompt for username and password
					attemptRegisterUser(s, dao);
					break;
				case 3:
					System.out.println("Goodbye.");
					System.exit(0);
					break;
				default:
					throw new NumberFormatException();

				}

			} catch (NumberFormatException e) {
				System.out.println("Error: input not an option.");
				System.out.println("Goodbye.");
				System.exit(0);
			}

			// check if username and password are valid

		} catch (Exception e) {

		}

	}
	
	*/

	private static void attemptLogIn(Scanner s, BankDao dao) {
		String username, password;
		System.out.print("Input username: ");
		username = s.next();
		System.out.print("Input password: ");
		password = s.next();
		try {
			dao.logIn(username, password);
		} catch (IncorrectUsernameException e) {
			System.out.println("Error: Username not found in database.");
		} catch (IncorrectPasswordException e) {
			System.out.println("Error: Password incorrect.");
		}
	}

	private static void attemptRegisterUser(Scanner s, BankDao dao) {
		String username, password;
		System.out.print("Input new username: ");
		username = s.next();
		System.out.print("Input new password: ");
		password = s.next();
		try {
			dao.registerNewUser(username, password);
		} catch (UsernameAlreadyUsedException e) {
			System.out.println("Error: Username already exists in database.");
		}
	}
}
