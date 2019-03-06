package com.computershop.controler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.computershop.database.Shopping_cart;
import com.computershop.database.User;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		DatabaseConn db = new DatabaseConn();
		Object o = session.getAttribute("logiran_user");
		User user = null;
		List<Shopping_cart> shoppingCart = null;

		if (o != null) {
			user = (User) o;
		} else {
			response.sendRedirect("ControlerServlet");
			return;
		}
		
		o = session.getAttribute("shopping_cart");
		
		if (o!=null) {
			shoppingCart = (ArrayList<Shopping_cart>) o;
		} else {
			response.sendRedirect("ControlerServlet");
			return;
		}
		
		saveShoppingCart(db, shoppingCart, user);
		updateLogoutTimestamp(db, user, session.getId());
		session.removeAttribute("shopping_cart");
		session.invalidate();

		response.sendRedirect("ControlerServlet");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void saveShoppingCart(DatabaseConn db, List<Shopping_cart> shoppingCart, User user) {

		try {
			db.connect();

			for (Shopping_cart cart : shoppingCart) {
				String query = "INSERT INTO shopping_cart (user_id, item_id, item_amount)" + " VALUES (" + user.get_id()
						+ ", '" + cart.getItem_id() + "', '" + cart.getItem_amount() + "')";
				db.queryUPDATE(query);
			}

			db.disconnect();
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void updateLogoutTimestamp(DatabaseConn db, User user, String sessionId) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String logout_timestamp = dateFormat.format(date);

		try {
			db.connect();

			String query = "UPDATE login_history SET logout_timestamp = '" + logout_timestamp + "' "
					+ " WHERE (user_id= " + user.get_id() + ") AND (session_id = '" + sessionId + " ')";
			db.queryUPDATE(query);

			db.disconnect();
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
