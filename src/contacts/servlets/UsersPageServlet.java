package contacts.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

@WebServlet(urlPatterns = { "/users" })
public class UsersPageServlet extends HttpServlet {

	private static final long serialVersionUID = 4833919591350164618L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoggedUser(session);

		String errorString = null;

		Connection conn = null;
		List<UserAccount> usersList = null;

		int ileUserow = 0;

		if (user == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {

			try {
				conn = MyUtils.makeAndBackConnection(request);

				usersList = DatabaseUtils.getUserList(conn);

				ileUserow = usersList.size();

			} catch (ClassNotFoundException | SQLException e) {
				errorString = "B³¹d podczas pobierania listy u¿ytkowników";
			}

		}

		System.out.println(ileUserow);
		MyConnectionUtils.closeMyConnection(conn);

		request.setAttribute("ile", ileUserow);
		request.setAttribute("errorString", errorString);
		request.setAttribute("userList", usersList);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/users.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
