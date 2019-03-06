package com.computershop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConn {

	public Connection connection;
	public Statement statement;

	public DatabaseConn() {
		this.connection = null;
		this.statement = null;
	}

	public void connect() throws SQLException, ClassNotFoundException {
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // za SQL server
		Class.forName("com.mysql.jdbc.Driver"); // za mySQL ispravno mysql-connector-java 5.1.47

//		connection = DriverManager.getConnection( "jdbc:sqlserver://localhost", "sa", "SQL" ); // za SQL server
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computershopdb", "root", "gLE8mp314gg25");
		statement = connection.createStatement();
		statement.executeUpdate("USE computershopdb");
	}

	public void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}

	public ResultSet querySELECT(String s) throws SQLException {
		return statement.executeQuery(s);
	}

	public int queryUPDATE(String s) throws SQLException {
		return statement.executeUpdate(s);
	}

}
