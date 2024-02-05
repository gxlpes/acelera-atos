package br.com.atos.projetojsf.core.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {

	private static final String url = "jdbc:mysql://localhost:3306/atosjavaweb";
	private static final String user = "root";
	private static final String password = "root";
	
	private static Connection conn;
	
	public static Connection getConnection() {
		
		try {
			
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			
			if(conn == null) {
				
				conn = DriverManager.getConnection(url, user, password);
				return conn;
			}else {
				return conn;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
