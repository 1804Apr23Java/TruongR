package revature.com.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import revature.com.util.ConnectionUtil;

public class CodeChallengeDaoImpl implements CodeChallengeDao {

	private String filename = "connection.properties";

	@Override
	public void getDeptAvgSalary() {

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement

			String sql = "SELECT DEPARTMENT.DEPARTMENT_NAME, AVG(EMPLOYEE.SALARY) AS AVG_SALARY FROM\n"
					+ "	EMPLOYEE INNER JOIN DEPARTMENT ON EMPLOYEE.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID\n"
					+ "	GROUP BY EMPLOYEE.DEPARTMENT_ID, DEPARTMENT.DEPARTMENT_NAME";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String deptName = rs.getString("DEPARTMENT_NAME");
				double avgSalary = rs.getDouble("AVG_SALARY");
				System.out.printf("Employees belonging to the %s department have an average salary of $%.2f.\n",
						deptName, avgSalary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void giveDeptWideRaise(int deptID) {
		CallableStatement cstmt = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			double newAvgSalary;
			int validDept;

			// use a prepared statement
			cstmt = con.prepareCall("{call sp_give_raise(?,?,?)}");
			cstmt.setInt(1, deptID);
			cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
			cstmt.registerOutParameter(3, java.sql.Types.INTEGER);

			cstmt.executeUpdate();

			newAvgSalary = cstmt.getDouble(2);
			validDept = cstmt.getInt(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
