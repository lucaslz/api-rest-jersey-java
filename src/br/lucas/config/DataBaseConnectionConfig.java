package br.lucas.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataBaseConnectionConfig {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/notas_db", "root", "root");
	}
}
