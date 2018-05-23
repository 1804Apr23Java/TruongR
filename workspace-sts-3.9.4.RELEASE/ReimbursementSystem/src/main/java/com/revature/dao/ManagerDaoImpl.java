package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.*;
import com.revature.exception.RequestAlreadyResolvedException;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	private String filename = "connection.properties";

	/*
	 * public Employee addEmployee(String username, String password, String
	 * firstName, String lastName, String address, String city, String state, String
	 * zip, String phone, String email) { PreparedStatement pstmt = null; String sql
	 * = null; ResultSet rs = null; Employee emp = null;
	 * 
	 * try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
	 * 
	 * sql =
	 * "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL) "
	 * + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)"; pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, username); pstmt.setString(2, password);
	 * pstmt.setString(3, firstName); pstmt.setString(4, lastName);
	 * pstmt.setString(5, address); pstmt.setString(6, city); pstmt.setString(7,
	 * state); pstmt.setString(8, zip); pstmt.setString(9, phone);
	 * pstmt.setString(10, email); pstmt.executeQuery();
	 * 
	 * sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?AND PASSWORD = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, username); pstmt.setString(2,
	 * password); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) emp = new Employee(rs.getInt("EMPLOYEEID"),
	 * rs.getString("USERNAME"), rs.getString("PASSWORD"),
	 * rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"),
	 * rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
	 * rs.getString("PHONE"), rs.getString("EMAIL"));
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * return emp;
	 * 
	 * }
	 */

	public List<Employee> getAllEmployees() {

		List<Employee> allEmployees = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			allEmployees = new ArrayList<Employee>();

			sql = "SELECT * FROM EMPLOYEE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next())
				allEmployees.add(new Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"),
						rs.getString("PASSWORD"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
						rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
						rs.getString("PHONE"), rs.getString("EMAIL")));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allEmployees;
	}

	@Override
	public Request resolveRequest(int requestId, boolean isApproved, int managerId)
			throws RequestAlreadyResolvedException {
		Request req = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int status = isApproved ? 1 : 2;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "SELECT * FROM REQUEST WHERE REQUESTID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("STATUS") != 0)
					throw new RequestAlreadyResolvedException(
							"Error: attempted to resolve requestid " + requestId + ", which is already resolved.");
				else {
					sql = "UPDATE REQUEST SET MANAGERID = ?, STATUS = ? WHERE REQUESTID = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, managerId);
					pstmt.setInt(2, status);
					pstmt.setInt(3, requestId);
					pstmt.executeUpdate();
					req = new Request(requestId, rs.getInt("EMPLOYEEID"), rs.getDouble("AMOUNT"),
							rs.getTimestamp("REQUESTTIME"), rs.getString("IMAGELINK"), status, managerId);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return req;
	}

	@Override
	public List<Request> getAllPendingRequests() {
		List<Request> pendingRequests = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			pendingRequests = new ArrayList<Request>();

			sql = "SELECT * FROM REQUEST WHERE STATUS = 0 ORDER BY REQUESTID ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
				int employeeId = rs.getInt("EMPLOYEEID");
				double amount = rs.getDouble("AMOUNT");
				Timestamp requestTime = rs.getTimestamp("REQUESTTIME");
				String imageLink = rs.getString("IMAGELINK");

				pendingRequests.add(new Request(requestId, employeeId, amount, requestTime, imageLink));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pendingRequests;
	}

	@Override
	public List<Request> getAllResolvedRequests() {
		List<Request> resolvedRequests = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			resolvedRequests = new ArrayList<Request>();

			sql = "SELECT * FROM REQUEST WHERE STATUS <> 0 ORDER BY REQUESTID ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
				int employeeId = rs.getInt("EMPLOYEEID");
				double amount = rs.getDouble("AMOUNT");
				Timestamp requestTime = rs.getTimestamp("REQUESTTIME");
				String imageLink = rs.getString("IMAGELINK");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				resolvedRequests
						.add(new Request(requestId, employeeId, amount, requestTime, imageLink, status, managerId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resolvedRequests;
	}

	@Override
	public List<Request> getAllEmployeeRequests(int employeeId) {
		List<Request> employeeRequests = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			employeeRequests = new ArrayList<Request>();

			sql = "SELECT * FROM REQUEST WHERE EMPLOYEEID = ? ORDER BY REQUESTID ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
				double amount = rs.getDouble("AMOUNT");
				Timestamp requestTime = rs.getTimestamp("REQUESTTIME");
				String imageLink = rs.getString("IMAGELINK");
				int status = rs.getInt("STATUS");
				if (status == 0) {
					employeeRequests.add(new Request(requestId, employeeId, amount, requestTime, imageLink));
				} else {
					int managerId = rs.getInt("MANAGERID");
					employeeRequests
							.add(new Request(requestId, employeeId, amount, requestTime, imageLink, status, managerId));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return employeeRequests;
	}
}
