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
			} else {
				throw new BankClientNotFoundException();
			}

		} catch (BankClientNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to access nonexistent client.");
			System.exit(1);
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
	public void updateBankClientUsername(int bankClientID, String newUsername) throws UsernameAlreadyUsedException {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new BankClientNotFoundException();
			}

			sql = "SELECT USERNAME FROM BANKCLIENT";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String curUsername = rs.getString("USERNAME");
				if (curUsername.equals(newUsername)) {
					throw new UsernameAlreadyUsedException();
				}
			}

			sql = "UPDATE BANKCLIENT SET USERNAME = ? WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newUsername);
			pstmt.setInt(2, bankClientID);
			rs = pstmt.executeQuery();

		} catch (BankClientNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to access nonexistent client.");
			System.exit(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateBankClientPassword(int bankClientID, String newPassword) {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new BankClientNotFoundException();
			}

			sql = "UPDATE BANKCLIENT SET PASSWORD = ? WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, bankClientID);
			rs = pstmt.executeQuery();

		} catch (BankClientNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to access nonexistent client.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createBankClient(String username, String password) throws UsernameAlreadyUsedException {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT USERNAME FROM BANKCLIENT";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("USERNAME").equals(username)) {
					throw new UsernameAlreadyUsedException();
				}
			}

			sql = "INSERT INTO BANKCLIENT (USERNAME, PASSWORD) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBankClient(int bankClientID) {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new BankClientNotFoundException();
			}

			sql = "DELETE FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			rs = pstmt.executeQuery();

		} catch (BankClientNotFoundException e) {
			// Should not actually be called in actual run
			System.out.println("Error: Attempted to access nonexistent client.");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
