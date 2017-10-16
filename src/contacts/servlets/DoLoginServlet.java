package contacts.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contacts.connectionUtils.MyConnectionUtils;
import contacts.models.UserAccount;
import contacts.utils.DatabaseUtils;
import contacts.utils.MyUtils;

//action coming from form login.jsp
@WebServlet(urlPatterns = { "/doLogin" })

public class DoLoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1905299329332612465L;

	public DoLoginServlet() {
		super();
	}

	/**
	 * handle GET request
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		
		//take parameter from form inputs
		//(.getParameter method doing this)
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserAccount user = null;
		boolean hasErrors = false;
		String stringError = null;

		Connection conn = null;

		if (username == null || password == null || username.length() == 0 || password.length() == 0) {
			hasErrors = true;
			stringError = "U¿ytkownik i has³o s¹ wymagane";
		} else {

			try {

				conn = MyUtils.makeAndBackConnection(request);

				user = DatabaseUtils.findUser(conn, username, password);

				if (user == null) {
					hasErrors = true;
					stringError = "Niepoprawna nazwa u¿ytkownika lub has³o";
				}

			} catch (SQLException | ClassNotFoundException eSql) {
				eSql.printStackTrace();
				hasErrors = true;
				stringError = eSql.getMessage();
			}

		}

		if (hasErrors) {
			user = new UserAccount();
			user.setUsername(username);
			user.setPassword(password);

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		} else {

			HttpSession session = request.getSession();
			MyUtils.rememberLoggedUser(session, user);

			MyConnectionUtils.closeMyConnection(conn);

			response.sendRedirect(request.getContextPath() + "/main");
		}

	}

	/**
	 * Obsluga POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
