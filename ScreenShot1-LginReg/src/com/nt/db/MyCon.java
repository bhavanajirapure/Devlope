package com.nt.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyCon {
	public static Connection con=null;
	public static Connection getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb2", "webdb");
		} catch (Exception e) {
			e.printStackTrace();
		}
return con;
}

}

