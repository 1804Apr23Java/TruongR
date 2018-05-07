package com.revature.dao;

import java.io.IOException;
import java.sql.*;

import com.revature.domain.*;
import com.revature.util.*;

public class BankClientDaoPLSQL implements BankClientDao {

	private String filename = "connection.properties";

	@Override
	public Account createAccount(int bankClientID) {
		/*
		 * Makes an account with no money in it.
		 */
		
		Account account = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "INSERT INTO ACCOUNT (ACCOUNTBALANCE, BANKCLIENTID) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, 0.0);
			pstmt.setInt(2, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTBALANCE = ? AND "
					+ "BANKCLIENTID = ? ORDER BY ACCOUNTID DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, 0.0);
			pstmt.setInt(2, bankClientID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				account = new Account(rs.getInt("ACCOUNTID"), rs.getDouble("ACCOUNTBALANCE"));

				sql = "{call tx_insert(?,?)}";
				cstmt = con.prepareCall(sql);
				cstmt.setInt(1, bankClientID);
				cstmt.setString(2, String.format("Created account %09d", account.getAccountID()));
				cstmt.executeUpdate();
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// Mostly for debugging, this shouldn't come up in an actual run of the console?
			System.out.println("Error: Attempted to add account to nonexistent user.");
			// System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return account;
	}


	@Override
	public void viewAccounts(int bankClientID) {
		/*
		 * Prints out all of the bankClient's accounts.
		 */
		Account account = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement

			String sql = "SELECT * FROM ACCOUNT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("Printing accounts for BankClient " + bankClientID + ".");
			while (rs.next()) {
				account = new Account(rs.getInt("ACCOUNTID"), rs.getDouble("ACCOUNTBALANCE"));
				System.out.printf("Account ID %09d has $%.2f.\n", account.getAccountID(), account.getBalance());
			}

			if (account == null) {
				System.out.println("No accounts found for this user.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAccount(int bankClientID, int accountID) throws MoneyInAccountException {
		/*
		 * Deletes an empty account belonging to the user. That the accountID belongs to the
		 * bankClient is a precondition.
		 */
		Account account = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				account = new Account(rs.getInt("ACCOUNTID"), rs.getDouble("ACCOUNTBALANCE"));
			}

			if (account == null) {
				throw new AccountNotFoundException();
			}

			if (account.getBalance() != 0.0) {
				throw new MoneyInAccountException();
			}

			sql = "DELETE FROM ACCOUNT WHERE ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			rs = pstmt.executeQuery();
			System.out.printf("Deleted account %09d.\n", accountID);

			sql = "{call tx_insert(?,?)}";
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, bankClientID);
			cstmt.setString(2, String.format("Deleted account %09d.", accountID));
			cstmt.executeUpdate();

		} catch (AccountNotFoundException e) {
			// Should not actually be able to be called in actual run
			System.out.println("Error: Attempted to delete account that does not exist in database.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deposit(int bankClientID, double amt, int accountID) {
		/*
		 * Deposits money into one of the bankClient's accounts. The accountID belonging
		 * to the bankClient is a precondition.
		 */

		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "UPDATE ACCOUNT SET ACCOUNTBALANCE = ACCOUNTBALANCE + ? WHERE ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, amt);
			pstmt.setInt(2, accountID);
			ResultSet rs = pstmt.executeQuery();

			sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.printf("Your balance after this deposit is: $%.2f.\n", rs.getDouble("ACCOUNTBALANCE"));

				sql = "{call tx_insert(?,?)}";
				cstmt = con.prepareCall(sql);
				cstmt.setInt(1, bankClientID);
				cstmt.setString(2, String.format("Deposited $%.2f into account %09d", amt, accountID));
				cstmt.executeUpdate();

			} else {
				throw new AccountNotFoundException();
			}

		} catch (AccountNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to deposit to account that does not exist in database.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void withdraw(int bankClientID, double amt, int accountID) throws OverdraftException {
		/*
		 * Attempts to withdraw from one of the bankClient's accounts, with exception
		 * handling for overdrafts. The accountID belonging to the bankClient is a
		 * precondition.
		 */

		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				double currentBalance = rs.getDouble("ACCOUNTBALANCE");
				if (amt > currentBalance)
					throw new OverdraftException();
			} else {
				throw new AccountNotFoundException();
			}

			sql = "UPDATE ACCOUNT SET ACCOUNTBALANCE = ACCOUNTBALANCE - ? WHERE ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, amt);
			pstmt.setInt(2, accountID);
			rs = pstmt.executeQuery();

			sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.printf("Your balance after this withdrawl is: $%.2f.\n", rs.getDouble("ACCOUNTBALANCE"));

				sql = "{call tx_insert(?,?)}";
				cstmt = con.prepareCall(sql);
				cstmt.setInt(1, bankClientID);
				cstmt.setString(2, String.format("Withdrew $%.2f from account %09d", amt, accountID));
				cstmt.executeUpdate();
			} else {
				throw new AccountNotFoundException();
			}

		} catch (AccountNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to deposit to account that does not exist in database.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getUserHistory(int bankClientID) {
		/*
		 * Takes in a bankClientID, and returns all records of transaction in the
		 * transaction table.
		 */

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "SELECT * FROM TRANSACTIONS WHERE BANKCLIENTID = ? ORDER BY TX_TIME DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String msg = rs.getString("MESSAGE");
				String time = rs.getTime("TX_TIME").toString();
				System.out.println("At " + time + ": " + msg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
