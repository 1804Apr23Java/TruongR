package com.revature.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.*;
import com.revature.util.ConnectionUtil;

public class AdminDaoPLSQL implements AdminDao {

	private String filename = "connection.properties";

	@Override
	public BankClient getBankClient(int bankClientID) {

		BankClient client = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				sql = "SELECT * FROM ACCOUNT WHERE BANKCLIENTID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bankClientID);
				rs = pstmt.executeQuery();
				List<Account> accountList = new ArrayList<Account>();

				while (rs.next()) {
					accountList.add(new Account(rs.getInt("ACCOUNTID"), rs.getDouble("ACCOUNTBALANCE")));
				}

				return new BankClient(username, password, accountList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return client;
	}

	@Override
	public List<BankClient> getAllBankClients() {

		List<BankClient> listOfClients = new ArrayList<BankClient>();

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT";
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				int bankClientID = rs.getInt("BANKCLIENTID");

				sql = "SELECT * FROM ACCOUNT WHERE BANKCLIENTID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bankClientID);

				ResultSet rs2 = pstmt.executeQuery();

				List<Account> accountList = new ArrayList<Account>();

				while (rs2.next()) {
					int accountID = rs2.getInt("ACCOUNTID");
					double balance = rs2.getDouble("ACCOUNTBALANCE");
					Account accountToAdd = new Account(accountID, balance);

					accountList.add(accountToAdd);
				}

				listOfClients.add(new BankClient(username, password, accountList));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listOfClients;
	}

	@Override
	public void updateBankClientUsername(int bankClientID, String newUsername) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBankClientPassword(int bankClientID, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createBankClient(int bankClientID, String username, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBankClient(int bankClientID) {
		// TODO Auto-generated method stub

	}

}
