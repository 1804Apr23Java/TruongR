package com.revature.main;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.*;
import com.revature.domain.*;

public class BankRunner {

	public static final int MAX_TRIES = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AdminDao aDao = new AdminDaoPLSQL();
		BankClientDao bcDao = new BankClientDaoPLSQL();

		try (Scanner s = new Scanner(System.in)) {

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
					logIn(s, aDao, bcDao);
					break;
				case 2:
					// prompt for username and password
					registerUser(s, aDao, bcDao);
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

	private static void logIn(Scanner s, AdminDao aDao, BankClientDao bcDao) {
		int counter = 0;
		while (counter < 5) {
			String username, password;
			System.out.print("Input username: ");
			username = s.next();
			System.out.print("Input password: ");
			password = s.next();
			try {

				int adminID = aDao.loginAdmin(username, password);
				int userID = aDao.loginUser(username, password);
				if (adminID != 0) {
					System.out.println("Credentials accepted. Welcome, admin " + username + ".");
					runAsAdmin(adminID, aDao, bcDao);
				} else if (userID != 0) {
					System.out.println("Credentials accepted. Welcome, client " + username + ".");
					runAsClient(userID, aDao, bcDao);
				} else {
					throw new IncorrectCredentialsException();
				}

			} catch (IncorrectCredentialsException e) {
				counter++;
				System.out.println("Error: Credentials invalid. You have " + (MAX_TRIES - counter) + " tries left.");
			}

		}
		System.out.println("Error: Too many failed login attempts.");
		System.exit(1);
	}

	private static void registerUser(Scanner s, AdminDao aDao, BankClientDao bcDao) {
		int counter = 0;
		while (counter < MAX_TRIES) {
			String username, password;
			System.out.print("Input new username: ");
			username = s.next();
			System.out.print("Input new password: ");
			password = s.next();
			try {
				System.out.println("Credentials accepted. Welcome, client " + username + ".");
				int userID = aDao.createBankClient(username, password);
				runAsClient(userID, aDao, bcDao);
			} catch (UsernameAlreadyUsedException e) {
				counter++;
				System.out.println(
						"Error: Username already exists in database. " + (MAX_TRIES - counter) + " tries remaining.");
			}
		}
		System.out.println("Error: Too many failed registration attempts.");
		System.exit(1);

	}

	private static void runAsClient(int userID, AdminDao aDao, BankClientDao bcDao) {
		System.out.println("You are a client!");
		boolean running = true;
		
		System.exit(0);
	}

	private static void runAsAdmin(int adminID, AdminDao aDao, BankClientDao bcDao) {
		System.out.println("You are an admin!");
		boolean running = true;
		
		System.exit(0);
	}
}
