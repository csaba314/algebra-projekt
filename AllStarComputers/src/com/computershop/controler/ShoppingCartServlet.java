package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Item_db;
import com.computershop.database.Shopping_cart;
import com.computershop.database.User;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCartServlet() {
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
		User anonimni = (User) session.getAttribute("anonimni");
		List<Shopping_cart> shopping_cart = new ArrayList<Shopping_cart>();
		int item_id = 0;
		int item_amount = 0;

		if (user == null && anonimni != null) {
			user = anonimni;

		} else if (user == null && anonimni == null) {
			response.sendRedirect("ControlerServlet");
			return;
		}

		String s1 = request.getParameter("dodaj_item_id");
		String s2 = request.getParameter("dodaj_item_amount");

		if (s1 != null && s2 != null) {
			item_id = Integer.parseInt(s1);
			item_amount = Integer.parseInt(s2);

			shopping_cart = (ArrayList<Shopping_cart>) session.getAttribute("shopping_cart");

			if (shopping_cart == null) {
				shopping_cart = new ArrayList<Shopping_cart>();
			}

			Item_db item = getItem(db, item_id);

			if (item != null) {
				
				for (Shopping_cart sc : shopping_cart) {
					if (sc.getItem_id() == item_id) {
						int newAmount = sc.getItem_amount() + item_amount;
						sc.setItem_amount(newAmount);
//						session.setAttribute("shopping_cart", shopping_cart);
						response.sendRedirect("ControlerServlet");
						return;
											}
					
				}
				Shopping_cart newCartEntry = new Shopping_cart(0, user.get_id(), item_id, item_amount);
				newCartEntry.setItemObject(item);
				shopping_cart.add(newCartEntry);
				
				session.setAttribute("shopping_cart", shopping_cart);
				session.setAttribute("brojProizvodaKosarica", shopping_cart.size());
			}
		}
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

	private Item_db getItem(DatabaseConn db, int item_id) {

		try {
			db.connect();
			String query = "SELECT * FROM item WHERE _id = " + item_id;
			Item_db currentItem = null;

			ResultSet rs = db.querySELECT(query);
			
			if (rs.next()) {

			currentItem = new Item_db(rs.getInt("_id"), rs.getString("item_name"),
					rs.getInt("item_category_id"), rs.getInt("item_subcategory_id"), rs.getString("item_description"),
					rs.getInt("item_onstock"), rs.getInt("item_price"));
			}
			rs.close();
			db.disconnect();
			return currentItem;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
