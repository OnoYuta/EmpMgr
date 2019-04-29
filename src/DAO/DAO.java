package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private final String DB_URL = "jdbc:h2:~/emp_info_manager";
	private final String USER = "sa";
	private final String PASS = "";
	public Connection conn;

	//データベース接続
	public void getConn() {
		try {
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("接続に成功しました");
		} catch (SQLException e) {
			System.out.println("接続に失敗しました");
			e.printStackTrace();
		}
	}

	//データベース切断
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
