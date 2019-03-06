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

/**
 * Servlet implementation class KupiServlet
 */
@WebServlet("/KupiServlet")
public class KupiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KupiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DatabaseConn db = new DatabaseConn();
		HttpSession session = request.getSession();

		List<Shopping_cart> shoppingCart = (ArrayList<Shopping_cart>) session.getAttribute("shopping_cart");
		int nacin_placanja = Integer.parseInt(request.getParameter("nacin_placanja"));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String purchase_timestamp = dateFormat.format(date);
		User tempUser = (User) session.getAttribute("logiran_user");

		if (tempUser == null) {
			response.sendRedirect("ControlerServlet");
			return;
		}

		for (Shopping_cart cartItem : shoppingCart) {

			
			try {
				db.connect();
				String query = "INSERT INTO purchase_history (item_id, amount, item_price, user_id, purchase_timestamp, nacin_placanja_id)"
						+ " VALUES (" + cartItem.getItem_id() + ", " + cartItem.getItem_amount() + ", "
						+ cartItem.getItemObject().getItem_price() + ", " + tempUser.get_id() + ", '"
						+ purchase_timestamp + "', " + nacin_placanja + ")";
				db.queryUPDATE(query);

				db.disconnect();
			} catch (Exception e) {
				Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			}

			try {
				db.connect();

				String query = "UPDATE item SET item_onstock="
						+ (cartItem.getItemObject().getItem_onstock() - cartItem.getItem_amount()) + " WHERE _id = "
						+ cartItem.getItem_id();
				db.queryUPDATE(query);

				db.disconnect();
			} catch (Exception e) {
				Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			}

		}
		session.setAttribute("shopping_cart", new ArrayList<Shopping_cart>());
		session.setAttribute("brojProizvodaKosarica", 0);

		response.sendRedirect("ControlerServlet");

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

}
