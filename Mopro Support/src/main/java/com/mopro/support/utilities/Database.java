package com.mopro.support.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mopro.support_base.Driver;

public class Database extends Driver {

	public static void main(String[] args) throws Exception {

		getData("support_form_data", 1,2);
	}

	public static Connection getConnection() throws Exception {
		
		Connection con = null;
		try {
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/seleniumtesting?serverTimezone=UTC";
			String username = "";
			String password = "";
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}

	public static void getData(String table, int rowNumber, int colNumber) {
		
		String tableName = table;
		String rowNum = Integer.toString(rowNumber);
		int colNum = colNumber;
		String stmt = "SELECT * FROM `" + tableName + "` WHERE "+ rowNum ;
		
		try {
			Connection con = getConnection();
			Statement stx = con.createStatement();
			ResultSet result = stx.executeQuery(stmt);
			
			List <String> data = new ArrayList<String>();
			
			
			
			while (result.next()) {				
				System.out.println(result.getString(colNum));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
