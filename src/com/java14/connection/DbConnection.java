package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	public static final String url 		="jdbc:mysql://localhost:3306/crm_final";
	public static final String username ="root";
	public static final String password ="quocdat16061990";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi ket noi driver!");
			e.printStackTrace();
			System.out.println("Loi ket noi driver");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi ket noi database!");
			e.printStackTrace();
		}

		return null;
	}
}
