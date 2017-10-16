package contacts.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import contacts.connectionUtils.MyConnectionUtils;
import contacts.models.UserAccount;

public class MyUtils {

	public static final String CONNECTION = "CONNECTION";

	public static void rememberConnection(ServletRequest request, Connection conn) {
		
		request.setAttribute(CONNECTION, conn);
	}

	public static Connection getRememberedConnection(ServletRequest request) {
		
		Connection conn = (Connection) request.getAttribute(CONNECTION);
		return conn;
	}

	public static void rememberLoggedUser(HttpSession session, UserAccount user) {
		session.setAttribute("logged", user);
	}
	
	public static UserAccount getLoggedUser(HttpSession session) {
		UserAccount logged = (UserAccount)session.getAttribute("logged");
		return logged;
	}
	
	public static Connection makeAndBackConnection(HttpServletRequest request)
		throws ClassNotFoundException, SQLException {
		
		Connection conn = MyConnectionUtils.getMyConnection();
		MyUtils.rememberConnection(request, conn);
		
		return MyUtils.getRememberedConnection(request);
	}
}
