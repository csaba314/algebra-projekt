package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.computershop.database.Item_db;
import com.computershop.database.Purchase_history;
import com.computershop.database.User;

/**
 * Servlet implementation class HistoryShoppingServlet
 */
@WebServlet("/HistoryShoppingServlet")
public class HistoryShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryShoppingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User tempUser = (User) session.getAttribute("logiran_user");
		DatabaseConn db = new DatabaseConn();
		List<Purchase_history> purchaseList = new ArrayList<>();
		String sort = request.getParameter("sortiraj");
		int sortiraj = 0;

		if (sort != null) {
			sortiraj = Integer.parseInt(sort);
		}

		if (tempUser == null) {
			response.sendRedirect("ControlerServlet");
		}
		String querry = "SELECT * FROM purchase_history ";

		if (tempUser.getUser_level_id() == 1) {
			querry += " WHERE user_id =" + tempUser.get_id();
		}

		switch (sortiraj) {
		case 1:
		case 5:
			querry += " ORDER BY purchase_timestamp DESC";
			break;
		case 2:
		case 6:
			querry += " ORDER BY purchase_timestamp ";
			break;
		case 3:
			querry += " ORDER BY user_id ";
			break;
		case 4:
			querry += " ORDER BY user_id DESC";
			break;

		default:
			querry += " ORDER BY purchase_timestamp DESC";
			break;
		}

		try {
			db.connect();
			ResultSet rs = db.querySELECT(querry);
			while (rs.next()) {
				purchaseList.add(new Purchase_history(rs.getInt("_id"), rs.getInt("item_id"), rs.getInt("amount"),
						rs.getInt("item_price"), rs.getInt("user_id"), rs.getString("purchase_timestamp"),
						rs.getInt("nacin_placanja_id")));
			}
			rs.close();
			db.disconnect();

		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		if (purchaseList.size() > 0) {
			purchaseList = dodajObjekte(purchaseList);
		}

		int sum = 0;
		if (tempUser.getUser_level_id() == 1) {
			for (Purchase_history ph : purchaseList) {
				if (ph.getUser_id() == tempUser.get_id()) {
					sum += (ph.getAmount() * ph.getItem_price());
				}
			}
		} else {
			for (Purchase_history ph : purchaseList) {
				sum += (ph.getAmount() * ph.getItem_price());
			}
		}

		request.setAttribute("sveukupno", sum);
		request.setAttribute("purchase_history", purchaseList);
		request.getRequestDispatcher("purchaseHistory.jsp").forward(request, response);
		;
	}

	private List<Purchase_history> dodajObjekte(List<Purchase_history> purchaseHistory) {
		DatabaseConn db = new DatabaseConn();

		try {
			db.connect();
			for (Purchase_history ph : purchaseHistory) {

				ResultSet rs = db.querySELECT("SELECT * FROM item WHERE _id = " + ph.getItem_id());
				while (rs.next()) {
					ph.setItem_object(new Item_db(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getInt(6), rs.getInt(7)));
				}
				rs.close();
			}
			db.disconnect();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchaseHistory;
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
