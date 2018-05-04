package com.revature.dao;

import java.io.IOException;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Cave;
import com.revature.util.ConnectionUtil;

public class CaveDaoImpl implements CaveDao {

	private String filename = "connection.properties";

	@Override
	public List<Cave> getCaves() {
		List<Cave> c1 = new ArrayList<Cave>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a Statement - beware SQL injection
			String sql = "SELECT * FROM CAVE2";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// move through result set
			while (rs.next()) {
				int id = rs.getInt("CAVE_ID");
				String name = rs.getString("CAVE_NAME");
				int maxBears = rs.getInt("MAX_BEARS");
				c1.add(new Cave(id, name, maxBears));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c1;
	}

	@Override
	public Cave getCaveById(int id) {
		// TODO Auto-generated method stub
		Cave c = null;

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// using a prepared statement
			String sql = "SELECT * FROM CAVE2 WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Cave(id, rs.getString("CAVE_NAME"), rs.getInt("MAX_BEARS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	@Override
	public void addCave(String name, int maxBears) {

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "INSERT INTO CAVE2 (CAVE_NAME, MAX_BEARS) VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, maxBears);
			pstmt.executeQuery();
			//while (rs.next())
			//	System.out.println(new Cave(rs.getInt("CAVE_ID"), rs.getString("CAVE_NAME"), rs.getInt("MAX_BEARS")));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateCaveCapacity(int id, int maxBears) {

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "UPDATE CAVE2 SET MAX_BEARS = ? WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, maxBears);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateCaveName(int id, String name) {

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "UPDATE CAVE2 SET CAVE_NAME = ? WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCave(int id) {

		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			String sql = "DELETE FROM CAVE2 WHERE CAVE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
