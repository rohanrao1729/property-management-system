package util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import oracle.jdbc.driver.OracleDriver;

public class DBConnection {
	private static final String username = "system";
	private static final String password = "123";
	private static final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static OracleDriver driver = new OracleDriver();
	private static Connection conn = null;
	
	public static Connection openConnection() {
		if(conn!=null) {
			return conn;
		}
		else {
			try {
				DriverManager.registerDriver(driver);
				conn = DriverManager.getConnection(jdbcUrl,username,password);
				System.out.println("connection successfull");
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			return conn;
		}
	}
	

	
}
