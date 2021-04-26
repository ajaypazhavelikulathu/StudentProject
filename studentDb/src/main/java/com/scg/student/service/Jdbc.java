package main.java.com.scg.student.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException  {
		
		String url="jdbc:mysql://localhost:3306/student_database";
		String uname="root";
		String pass="password";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		con.setAutoCommit(false);
		return con;
		
	}

}