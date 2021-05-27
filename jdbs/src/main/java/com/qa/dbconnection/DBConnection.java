package com.qa.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qa.utils.DBConfig;

public class DBConnection {
	private String url = "jdbc:mysql://localhost:3306/jdbs?db_name&serverTimezone=UTC";
	private String user = "root";
	private String pw = "alalal";
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	
	public void create(String name) throws SQLException {
		try {
		con = DriverManager.getConnection(url, user, pw);
		String sql = "INSERT INTO people (name) VALUES (?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void read() throws SQLException {
		try {
		con = DriverManager.getConnection(url, user, pw);
		String sql = "SELECT * FROM people";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("No results found");
		} else {
			do {
				System.out.println(String.format("ID: %d, NAME: %s", rs.getInt("id"), rs.getString("name")));
			} while(rs.next());
		}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void read(int id) throws SQLException {
		try {
		con = DriverManager.getConnection(url, user, pw);
		String sql = "SELECT * FROM people WHERE id=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if (!rs.next()) {
			System.out.println("No results found");
		} else {
			do {
				System.out.println(String.format("ID: %d, NAME: %s", rs.getInt("id"), rs.getString("name")));
			} while (rs.next());
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, String change) throws SQLException {
		try {
	    con = DriverManager.getConnection(url, user, pw);
	    String sql = "UPDATE people SET name=? WHERE id=?";
	    ps = con.prepareStatement(sql);
	    ps.setInt(2, id);
	    ps.setString(1, change);
	    ps.executeUpdate();
		} catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public void delete(int id) throws SQLException {
		
		try {
		con = DriverManager.getConnection(url, user, pw);
		String sql = "DELETE FROM people WHERE id=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void tearDown() throws SQLException  {
		con.close();
	}
}
