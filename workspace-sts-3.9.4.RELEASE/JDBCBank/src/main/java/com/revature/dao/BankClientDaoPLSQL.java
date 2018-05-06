package com.revature.dao;

import java.io.IOException;
import java.sql.*;

import com.revature.domain.*;
import com.revature.util.*;

public class BankClientDaoPLSQL implements BankClientDao {

	private String filename = "connection.properties";

	@Override
	public Account createAccount(int bankClientId, double startingValue) {

		Account account = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "INSERT INTO ACCOUNT (ACCOUNTBALANCE, BANKCLIENTID) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, startingValue);
			pstmt.setInt(2, bankClientId);
			ResultSet rs = pstmt.executeQuery();

			sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTBALANCE = ? AND "
					+ "BANKCLIENTID = ? ORDER BY ACCOUNTID DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, startingValue);
			pstmt.setInt(2, bankClientId);
			rs = pstmt.executeQuery();

			// do something with result
			if (rs.next()) {
				account = new Account(rs.getInt("ACCOUNTID"), rs.getDouble("ACCOUNTBALANCE"));
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			// Mostly for debugging, this shouldn't come up in an actual run of the console?
			System.out.println("Error: Attempted to add account to nonexistent user.");
			//System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public Account createAccount(int bankClientId) {
		return createAccount(bankClientId, 0);
	}
	
	@Override
	public void viewAccounts(int bankClientId) {
		
	}

	@Override
	public void deleteAccount(int accountId) throws MoneyInAccountException {
		Account account = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement

			String sql = "SELECT * FROM ACCOUNT WHERE " + "ACCOUNTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
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
			pstmt.setInt(1, accountId);
			rs = pstmt.executeQuery();
			System.out.println("Deleted: " + account);

		} catch (AccountNotFoundException e) {
			//Should not actually be called in actual run
			System.out.println("Error: Attempted to delete account that does not exist in database.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deposit(double amt, int accountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(double amt, int accountId) throws OverdraftException {
		// TODO Auto-generated method stub

	}

}
