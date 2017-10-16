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

@WebServlet(urlPatterns = { "/del" })
public class DoDeleteUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7852683190170066227L;

	/**
	 * Obs³uga GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserAccount loggedUser = MyUtils.getLoggedUser(session);

		Connection conn = null;

		boolean hasErrors = false;
		String errorString = null;

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				conn = MyUtils.makeAndBackConnection(request);

				DatabaseUtils.deleteUser(conn, id);

			} catch (ClassNotFoundException | SQLException e) {
				hasErrors = true;
				errorString = "B³¹d podczas usuwania u¿ytkownika";
				System.out.println(e.getMessage());
			}
		}

		if (hasErrors) {
			request.setAttribute("errorString", errorString);
		}

		MyConnectionUtils.closeMyConnection(conn);
		
		response.sendRedirect(request.getContextPath() + "/users");

	}

	/**
	 * Obs³uga POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
