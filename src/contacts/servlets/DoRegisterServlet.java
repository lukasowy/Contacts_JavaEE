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

import contacts.connectionUtils.MyConnectionUtils;
import contacts.models.UserAccount;
import contacts.utils.DatabaseUtils;
import contacts.utils.ContactsUtils;
import contacts.utils.MyUtils;

@WebServlet(urlPatterns = { "/doRegister" })
public class DoRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 4579324542489511191L;

	public DoRegisterServlet() {
		super();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userFirstname = request.getParameter("userFirstname");
		String userLastname = request.getParameter("userLastname");

		
		String operation = request.getParameter("operation");
		String id = request.getParameter("idUser");
		
		UserAccount user = null;
		boolean hasErrors = false;
		String stringError = null;

		Connection conn = null;

		if (ContactsUtils.checkRegisterData(username, password, userFirstname, userLastname)) {
			
			hasErrors = true;
			stringError = "Pola oznaczone gwiazdk¹ s¹ wymagane!";
		} else {

			try {

				user = new UserAccount();

				if (operation.equals("M")) {
					user.setIdUser(Integer.parseInt(id));
				}

				user.setUsername(username);
				user.setPassword(password);
				user.setUserFirstname(userFirstname);
				user.setUserLastname(userLastname);
				user.setOperation(operation);

				conn = MyUtils.makeAndBackConnection(request);

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
			user.setUserFirstname(userFirstname);
			user.setUserLastname(userLastname);

			user.setOperation(operation);

			if (operation.equals("M")) {
				user.setIdUser(Integer.parseInt(id));
			}

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		} else {
			try {
				if (operation.equals("N")) {
					DatabaseUtils.registerUser(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/");
				} else if (operation.equals("M")) {
					DatabaseUtils.updateUserData(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/users");
				} else if (operation.equals("D")) {
					DatabaseUtils.registerUser(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/users");
				} else {
					stringError = "Nierozpoznana operacja!";
					response.sendRedirect(request.getContextPath() + "/users");
				}

			} catch (SQLException e) {
				System.out.println("B³¹d podczas zapisu do bazy danych!");
			}

		}

	}

	/**
	 * Obs³uga POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
