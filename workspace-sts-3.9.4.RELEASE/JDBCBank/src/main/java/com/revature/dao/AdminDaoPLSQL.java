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
	public int loginBankClient(String username, String password) {
		/*
		 * Validates a user's credentials, and returns their ID if it passes.
		 * Otherwise returns 0.
		 */

		int validAdmin = 0;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE USERNAME = ? AND PASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				validAdmin = rs.getInt("BANKCLIENTID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return validAdmin;
	}

	@Override
	public int loginAdmin(String username, String password) {
		/*
		 * Validates an admin's credentials, and returns their ID if it passes.
		 * Otherwise returns 0.
		 */
		
		int validAdmin = 0;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM ADMIN WHERE ADMINUSERNAME = ? AND ADMINPASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				validAdmin = rs.getInt("ADMINID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return validAdmin;
	}

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
			System.out.println("Error: Attempted to access nonexistent client.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return client;
	}

	@Override
	public List<BankClient> getAllBankClients() {
		/*
		 * Returns a List of every BankClient in the database.
		 */

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
	public void updateBankClientUsername(int bankClientID, String newUsername) 
			throws UsernameAlreadyUsedException {
		/*
		 * Changes a bankClient's username, but throws an exception if it's a duplicate username.
		 */
		
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new BankClientNotFoundException();
			}

			sql = "SELECT USERNAME FROM BANKCLIENT UNION SELECT ADMINUSERNAME FROM ADMIN";
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
			System.out.println("Error: Attempted to access nonexistent client.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateBankClientPassword(int bankClientID, String newPassword) {
		/*
		 * Changes a bankClient's password to the newPassword.
		 */

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
			System.out.println("Error: Attempted to access nonexistent client.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int createBankClient(String username, String password) throws UsernameAlreadyUsedException {
		/*
		 * Makes a bankClient and returns its ID.
		 */

		PreparedStatement pstmt = null;
		int bankClientID = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT USERNAME FROM BANKCLIENT UNION SELECT ADMINUSERNAME FROM ADMIN";
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

			sql = "SELECT BANKCLIENTID FROM BANKCLIENT WHERE USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bankClientID = rs.getInt("BANKCLIENTID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bankClientID;
	}

	@Override
	public void deleteBankClient(int bankClientID) {
		/*
		 * Deletes the bankClient of the specified bankclientID in the database. Does
		 * multiple deletes to cascade the removal of constraints.
		 */
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new BankClientNotFoundException();
			}

			sql = "DELETE FROM ACCOUNT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			rs = pstmt.executeQuery();

			sql = "DELETE FROM TRANSACTIONS WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			rs = pstmt.executeQuery();

			sql = "DELETE FROM BANKCLIENT WHERE BANKCLIENTID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bankClientID);
			rs = pstmt.executeQuery();

		} catch (BankClientNotFoundException e) {
			System.out.println("Error: Attempted to access nonexistent client.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
