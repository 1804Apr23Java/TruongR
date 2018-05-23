package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Manager;
import com.revature.beans.Request;
import com.revature.util.ConnectionUtil;
import com.revature.util.EmployeeField;

public class EmployeeDaoImpl implements EmployeeDao {

	private String filename = "connection.properties";

	@Override
	public Employee getEmployee(int employeeId) {

		Employee emp = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null, rs2 = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();

			sql = "SELECT * FROM MANAGER WHERE EMPLOYEEID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			rs2 = pstmt.executeQuery();

			if (rs.next())
				if (rs2.next())
					emp = new Manager(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),
							rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"), rs.getString("PHONE"),
							rs.getString("EMAIL"), rs2.getInt("MANAGERID"));
				else
					emp = new Employee(rs.getInt("EMPLOYEEID"), rs.getString("USERNAME"), rs.getString("PASSWORD"),
							rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"), rs.getString("PHONE"),
							rs.getString("EMAIL"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
		
		
	}

	@Override
	public Request submitRequest(int employeeId, double amount) {
		return submitRequest(employeeId, amount, null);
	}

	@Override
	public Request submitRequest(int employeeId, double amount, String imageLink) {
		Request req = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		Timestamp requestTime = Timestamp.from(Instant.now());

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "INSERT INTO REQUEST (EMPLOYEEID, AMOUNT, REQUESTTIME, IMAGELINK) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setDouble(2, amount);
			pstmt.setTimestamp(3, requestTime);
			pstmt.setString(4, imageLink);
			pstmt.executeUpdate();

			sql = "SELECT * FROM REQUEST WHERE EMPLOYEEID=? AND REQUESTTIME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setTimestamp(2, requestTime);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
				req = new Request(requestId, employeeId, amount, requestTime, imageLink);
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Attempted to add request from employee " + employeeId + " who doesn't exist.");
			// e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return req;
	}

	@Override
	public List<Request> getPendingRequests(int employeeId) {
		List<Request> pendingRequests = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			pendingRequests = new ArrayList<Request>();

			sql = "SELECT * FROM REQUEST WHERE EMPLOYEEID = ? AND STATUS = 0 ORDER BY REQUESTID ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
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
	public List<Request> getResolvedRequests(int employeeId) {
		List<Request> resolvedRequests = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			resolvedRequests = new ArrayList<Request>();

			sql = "SELECT * FROM REQUEST WHERE EMPLOYEEID = ? AND STATUS <> 0 ORDER BY REQUESTID ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int requestId = rs.getInt("REQUESTID");
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
	public Employee updateEmployee(int employeeId, EmployeeField field, String updateValue) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "UPDATE EMPLOYEE SET " + field.getFieldName() + " = ? WHERE EMPLOYEEID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateValue);
			pstmt.setInt(2, employeeId);
			pstmt.executeUpdate();

			emp = getEmployee(employeeId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
	}
	
	@Override
	public Employee login(String username, String password) {
		Employee emp = null;

		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next())
				emp = getEmployee(rs.getInt("EMPLOYEEID"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
	}

}
