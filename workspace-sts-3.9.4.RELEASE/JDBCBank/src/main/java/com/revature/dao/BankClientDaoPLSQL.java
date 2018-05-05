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

			System.out.println(con);
			
			// use a prepared statement
			String sql = "INSERT INTO ACCOUNT (ACCOUNTBALANCE, BANKCLIENTID) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, startingValue);
			pstmt.setInt(2, bankClientId);
			System.out.println("arrived at line 28");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("arrived at line 30");

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
	public void deleteAccount(int accountId) throws MoneyInAccountException {
		// TODO Auto-generated method stub

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
