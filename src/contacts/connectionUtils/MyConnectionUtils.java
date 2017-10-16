package contacts.connectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionUtils {

	public static Connection getMyConnection() throws ClassNotFoundException, SQLException {
		
		return MySQLConnectionUtils.makeSQLConnection();
	}
	
	public static void closeMyConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			
		}
	}
	
	public static void myRollback(Connection conn) {
		try {
			conn.rollback();
			
		}catch(Exception e) {
			
		}
	}
	
}
