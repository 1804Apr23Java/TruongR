package com.revature.main;

import java.io.IOException;
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
			System.out.println("Error with scanner.");
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
					runAsAdmin(adminID, s, aDao, bcDao);
				} else if (userID != 0) {
					System.out.println("Credentials accepted. Welcome, client " + username + ".");
					runAsClient(userID, s, aDao, bcDao);
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
				int userID = aDao.createBankClient(username, password);
				System.out.println("Credentials accepted. Welcome, client " + username + ".");
				runAsClient(userID, s, aDao, bcDao);
			} catch (UsernameAlreadyUsedException e) {
				counter++;
				System.out.println(
						"Error: Username already exists in database. " + (MAX_TRIES - counter) + " tries remaining.");
			}
		}
		System.out.println("Error: Too many failed registration attempts.");
		System.exit(1);

	}

	private static void runAsClient(int userID, Scanner s, AdminDao aDao, BankClientDao bcDao) {

		BankClient bc = aDao.getBankClient(userID);
		Account a;

		boolean running = true;
		while (running) {
			System.out.println("Please select an option from below:");
			System.out.println("1. Create a new account.");
			System.out.println("2. View balances of all accounts.");
			System.out.println("3. Delete an empty account.");
			System.out.println("4. Deposit.");
			System.out.println("5. Withdraw from an account.");
			System.out.println("6. Logout.");

			double amount;
			int option = s.nextInt();

			switch (option) {
			case 1:
				a = bcDao.createAccount(userID);
				System.out.println("Successfully added new account.");
				bc = aDao.getBankClient(userID);
				break;
			case 2:
				bcDao.viewAccounts(userID);
				break;
			case 3:
				try {
					if (bc.getAccountList().isEmpty()) {
						throw new NoAccountsException();
					}
					System.out.println(bc);
					System.out.println("Which account would you like to delete?");

					option = s.nextInt();

					bcDao.deleteAccount(bc.getAccountList().get(option - 1).getAccountID());
				} catch (MoneyInAccountException e) {
					System.out.println("Error: Attempted to delete a non-empty account.");
				} catch (NoAccountsException e) {
					System.out.println("Error: no accounts to delete from.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Error: invalid choice for account.");
				}
				bc = aDao.getBankClient(userID);
				break;
			case 4:

				try {
					if (bc.getAccountList().isEmpty()) {
						throw new NoAccountsException();
					}
					System.out.println(bc);
					System.out.println("To which account would do you wish to deposit to?");

					option = s.nextInt();

					int accID = bc.getAccountList().get(option - 1).getAccountID();

					System.out.println("Enter the amount that you would like to add.");

					amount = s.nextDouble();

					bcDao.deposit(amount, accID);

				} catch (NoAccountsException e) {
					System.out.println("Error: no accounts to delete from.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Error: invalid choice for account.");
				}
				bc = aDao.getBankClient(userID);
				break;
			case 5:
				try {
					if (bc.getAccountList().isEmpty()) {
						throw new NoAccountsException();
					}
					System.out.println(bc);
					System.out.println("From which account would do you wish to withdraw from?");

					option = s.nextInt();

					int accID = bc.getAccountList().get(option - 1).getAccountID();

					System.out.println("Enter the amount that you would like to withdraw.");

					amount = s.nextDouble();
					bcDao.withdraw(amount, accID);
				} catch (OverdraftException e) {
					System.out.println("Error: Attempted to withdraw more money than was in the account.");
				} catch (NoAccountsException e) {
					System.out.println("Error: no accounts to delete from.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Error: invalid choice for account.");
				}
				bc = aDao.getBankClient(userID);
				break;
			case 6:
				System.out.println("Thank you for using JDBC Bank. Have a fantastic day.");
				running = false;
				break;
			default:
				System.out.println("Invalid option selected.");
				break;
			}
		}

		System.exit(0);
	}

	private static void runAsAdmin(int adminID, Scanner s, AdminDao aDao, BankClientDao bcDao) {
		System.out.println("You are an admin!");
		int option, accID, userID;
		String newUsername, newPassword;

		boolean running = true;
		while (running) {
			System.out.println("Please select an option from below:");
			System.out.println("1. Retrieve information on a bank client.");
			System.out.println("2. Retrieve information on all bank clients.");
			System.out.println("3. Change a client's username.");
			System.out.println("4. Change a client's password.");
			System.out.println("5. Create a new client.");
			System.out.println("6. Delete a client.");
			System.out.println("7. Logout.");

			option = s.nextInt();

			switch (option) {
			case 1:
				System.out.println("Please enter the ID of the client you would like to view.");

				accID = s.nextInt();

				System.out.printf("Attempting to retrieve information on user %09d.\n", accID);
				BankClient bc = aDao.getBankClient(accID);

				if (bc == null)
					System.out.println("No user with that ID found.");
				else
					System.out.println(bc);

				break;
			case 2:
				System.out.println("Printing information on all bank clients.");
				List<BankClient> userList = aDao.getAllBankClients();
				for (BankClient b : userList)
					System.out.println(b);
				break;
			case 3:
				System.out.println("Please enter the ID of the client whose username you wish to change.");

				userID = s.nextInt();

				System.out.println("Please enter the new username you wish to change this to.");

				newUsername = s.next();

				try {
					System.out.println("Updating.");
					aDao.updateBankClientUsername(userID, newUsername);
				} catch (UsernameAlreadyUsedException e) {
					System.out.println("Error: Username in use by someone else.");
				}

				break;
			case 4:
				System.out.println("Please enter the ID of the client whose password you wish to change.");
				userID = s.nextInt();

				System.out.println("Please enter the new password you wish to change this to.");
				newPassword = s.next();

				System.out.println("Updating.");
				aDao.updateBankClientPassword(userID, newPassword);
				break;
			case 5:
				System.out.println("Please enter the username of the new client.");
				newUsername = s.next();

				System.out.println("Please enter the password of the new client.");
				newPassword = s.next();

				try {
					userID = aDao.createBankClient(newUsername, newPassword);
					System.out.println("Account created.");
				} catch (UsernameAlreadyUsedException e) {
					System.out.println("Error: Username already exists in database.");
				}

				break;
			case 6:
				
				System.out.println("Please enter the ID of the client you wish to delete.");
				userID = s.nextInt();
				
				aDao.deleteBankClient(userID);
				
				System.out.println("Deleted client and his data.");
				
				break;
			case 7:
				System.out.println("Thank you for using JDBC Bank. Have a fantastic day.");
				running = false;
				break;
			default:
				System.out.println("Invalid option selected.");
				break;
			}
		}

		System.exit(0);
	}
}
