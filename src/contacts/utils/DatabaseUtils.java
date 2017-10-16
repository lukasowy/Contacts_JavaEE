package contacts.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contacts.models.UserAccount;

public class DatabaseUtils {

	public static int users(Connection conn) throws SQLException {

		int ile = 0;

		String sql = "Select count(*) FROM users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sql);

			rs = stm.executeQuery();
			rs.last();
			ile = rs.getRow();

			System.out.println("Znaleziono u¿ytkowników: " + ile);

			rs.close();
			stm.close();

			return ile;

		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {

			}
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {

			}

			throw e;
		}
	}

	public static UserAccount findUser(Connection conn, String username, String password) throws SQLException {

		String sql = "Select iduser, user_username, user_password, user_firstname, user_lastname from Users "
				+ "where user_username = ? and user_password = ? ";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			UserAccount user = null;

			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password);

			rs = stm.executeQuery();

			if (rs.next()) {
				user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUsername(username);
				user.setPassword(rs.getString(3));
				user.setUserFirstname(rs.getString(4));
				user.setUserLastname(rs.getString(5));
			}
			rs.close();
			stm.close();

			return user;
		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {

			}
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {

			}

			throw e;
		}
	}

	public static int howManyUsers(Connection conn) throws SQLException {

		String sql = "Select iduser, user_username, user_password, user_firstname, user_lastname from Users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		int ile = 0;

		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			if (rs.next()) {
				ile = 1;
			} else {
				ile = 0;
			}

			rs.close();
			stm.close();

			System.out.println("Zarejestrowanych w bazie " + ile + " u¿ytkowników");
			return ile;

		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}

			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}
	}

	public static void registerUser(Connection conn, UserAccount user) throws SQLException {
		String sql = "Insert into users (user_username, user_password, user_firstname, user_lastname)"
				+ " values(?, ?, ?, ?)";

		PreparedStatement stm = null;

		try {

			stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getUserFirstname());
			stm.setString(4, user.getUserLastname());

			stm.execute();
			stm.close();

		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");

			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			throw e;
		}
	}

	public static List<UserAccount> getUserList(Connection conn) throws SQLException {

		List<UserAccount> userList = new ArrayList<UserAccount>();

		String sql = "Select * from Users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				UserAccount user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUserFirstname(rs.getString(2));
				user.setUserLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));

				userList.add(user);
			}

			rs.close();
			stm.close();

			return userList;

		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	public static UserAccount getUserData(int idUser, Connection conn) throws SQLException {

		UserAccount user = null;
		String sql = "Select * from Users where iduser = ?";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			stm = conn.prepareStatement(sql);
			stm.setInt(1, idUser);
			rs = stm.executeQuery();

			while (rs.next()) {
				user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUserFirstname(rs.getString(2));
				user.setUserLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));

			}

			rs.close();
			stm.close();

			return user;

		} catch (SQLException e) {
			System.out.println("B³¹d podczas wykonywania zapytania");

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	public static void updateUserData(Connection conn, UserAccount user) throws SQLException {

		String sql = "Update Users Set " + "user_username = ?, " + "user_password = ?," + "user_firstname = ?,"
				+ "user_lastname = ? " + "Where iduser = ?";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sql);

			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getUserFirstname());
			stm.setString(4, user.getUserLastname());
			stm.setInt(5, user.getIdUser());

			stm.executeUpdate();

			stm.close();

		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception e) {
				// nic nie rob
			}

		}

	}

	public static void deleteUser(Connection conn, int id) throws SQLException {

		String sql = "DELETE FROM users WHERE iduser = " + id;

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sql);

			stm.executeUpdate(sql);

			stm.close();

		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception e) {
				// nic nie rob
			}

		}

	}
}
