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

@WebServlet(urlPatterns = { "/reg" })
public class RegisterPageServlet extends HttpServlet {

	private static final long serialVersionUID = -8863312982014477558L;

	public RegisterPageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		UserAccount loggedUser = MyUtils.getLoggedUser(session);

		Connection conn = null;
		UserAccount user = null;

		boolean hasErrors = false;
		String errorString = null;
		int idUser = 0;

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {

			String operation = request.getParameter("mode");

			if (operation.equals("M")) {
				idUser = Integer.parseInt(request.getParameter("id"));

				try {
					conn = MyUtils.makeAndBackConnection(request);

					user = DatabaseUtils.getUserData(idUser, conn);

					user.setOperation(operation);
					user.setIdUser(idUser);

					MyConnectionUtils.closeMyConnection(conn);

				} catch (ClassNotFoundException | SQLException e) {
					hasErrors = true;
					errorString = "B³¹d podczas pobierania listy u¿ytkowników";
					System.out.println(e.getMessage());
				}
			} else {
				user = new UserAccount();
				user.setOperation(operation);
			} 

			if (hasErrors) {
				response.sendRedirect(request.getServletPath() + "/users");
			}

			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
