package contacts.connectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtils {

	public static Connection makeSQLConnection() {

		String hostname = "localhost";
		String dbName = "contacts";
		String username = "lukasowy";
		String rootPassword = "lukasowy";

		return makeSQLConnection(hostname, dbName, username, rootPassword);
	}

	public static Connection makeSQLConnection(String hostname, String dbName, String username, String rootPassword) {
		Connection conn = null;
		try {
			//Set up a driver
			Class.forName("com.mysql.jdbc.Driver");

			String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName + "?characterEncoding=UTF-8";

			conn = DriverManager.getConnection(connectionURL, username, rootPassword);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} 

		return conn;
	}

}
