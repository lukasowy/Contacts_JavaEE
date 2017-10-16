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

//The servlet must to known when will be involved
//The login is a induction
@WebServlet(urlPatterns = { "/login" })

public class LoginPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8514257027744624370L;

	public LoginPageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		req.setCharacterEncoding("utf-8");

		session.invalidate();

		int userAccounts = 0;
		Connection conn = null;

		try {

			conn = MyUtils.makeAndBackConnection(req);

			userAccounts = DatabaseUtils.howManyUsers(conn);

			MyConnectionUtils.closeMyConnection(conn);

		} catch (SQLException | ClassNotFoundException eSql) {
			eSql.printStackTrace();
		}

		if (userAccounts > 0) {
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			disp.forward(req, resp);
		} else {
			UserAccount user = new UserAccount();
			user.setOperation("N");
			req.setAttribute("user", user);
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			disp.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
