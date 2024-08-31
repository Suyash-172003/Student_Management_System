package com.thinking.machines.school.dl.dao.connection;
import java.sql.*;
public class DAOConnection
{
	public static Connection getConnection() throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db?allowPublicKeyRetrieval=true&useSSL=false","school","school");
	}

}
