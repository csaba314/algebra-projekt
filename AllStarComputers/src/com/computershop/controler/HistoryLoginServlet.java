package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Login_history;
import com.computershop.database.User;

/**
 * Servlet implementation class HistoryLoginServlet
 */
@WebServlet("/HistoryLoginServlet")
public class HistoryLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		DatabaseConn db = new DatabaseConn();
		User user = (User) session.getAttribute("logiran_user");
		List<Login_history> loginList = new ArrayList<>();
		String sortiranje = "";
		int sort = 0;

		if (request.getParameter("sortiraj") != null) {
			sort = Integer.parseInt(request.getParameter("sortiraj"));
		}
		switch (sort) {
		case 1:
			sortiranje = "login_timestamp";
			break;
		case 2:
			sortiranje = "login_timestamp DESC";
			break;
		case 3:
			sortiranje = "user_id";
			break;
		case 4:
			sortiranje = "user_id DESC";
			break;
		default:
			sortiranje = "login_timestamp DESC";
			break;

		}

		if (user.getUser_level_id() == 2) {

			String queryString = "SELECT * FROM login_history ORDER BY " + sortiranje;

			try {
				db.connect();
				ResultSet rs = db.querySELECT(queryString);
				while (rs.next()) {

					Login_history ls = (new Login_history(rs.getInt("_id"), rs.getInt("user_id"),
							rs.getString("login_timestamp"), rs.getString("session_id"),
							rs.getString("logout_timestamp")));
					ls.setUser(getUserFromDb(db, ls.getUser_id()));

					loginList.add(ls);
				}
				rs.close();
				db.disconnect();

			} catch (Exception e) {
				Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			}

			request.setAttribute("login_history", loginList);
			request.getRequestDispatcher("loginHistory.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private User getUserFromDb(DatabaseConn db, int userId) {
		User user = null;

		try {
			db.connect();
			ResultSet rs = db.querySELECT("SELECT * FROM user_ " + " WHERE _id = " + userId);

			if (rs.next()) {
				int _id = rs.getInt("_id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String adresa = rs.getString("adresa");
				String _email = rs.getString("email");
				String _password = rs.getString("password");
				int user_level_id = rs.getInt("user_level_id");
				int nacin_placanja_id = rs.getInt("nacin_placanja_id");

				user = new User(_id, firstName, lastName, adresa, _email, _password, user_level_id);
				user.setNacin_placanja_id(nacin_placanja_id);
			}
			rs.close();
			db.disconnect();

		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		return user;
	}

}
