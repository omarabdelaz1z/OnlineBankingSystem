package JDBC.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;

	private String dburl = "jdbc:mysql://localhost:3306/banksystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "root";
	private String pass = "root";

	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(dburl, user, pass);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance() {
		try {
			if (instance == null || instance.getConnection().isClosed()) {
				instance = new DatabaseConnection();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return instance;
	}
}
