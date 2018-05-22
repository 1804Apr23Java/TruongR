package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.domain.Employee;
import com.revature.domain.Manager;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private String filename = "connection.properties";

	@Override
	public Employee login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewEmployeeHomepage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void submitRequest(int employeeId, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void submitRequest(int employeeId, double amount, String imagelink) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewPendingRequests(int employeeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewResolvedRequests(int employeeId) {
		// TODO Auto-generated method stub

	}

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
	public void updateEmployee(int employeeId, int field, String updateValue) {
		// TODO Auto-generated method stub

	}

}
