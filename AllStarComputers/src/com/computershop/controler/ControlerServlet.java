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
import com.computershop.database.Item_category_db;
import com.computershop.database.Item_db;
import com.computershop.database.Item_subcategory_db;
import com.computershop.database.Shopping_cart;
import com.computershop.database.User;

/**
 * Servlet implementation class ControlerServlet
 */
@WebServlet(description = "Main entry point to the app", urlPatterns = { "/ControlerServlet", "/" })
public class ControlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DatabaseConn db = new DatabaseConn();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 60 * 24 * 365);

		String adress = "home.jsp";

		if (session.getAttribute("shopping_cart") == null) {
			session.setAttribute("shopping_cart", new ArrayList<Shopping_cart>());
		}

		if ((session.getAttribute("anonimni") == null) && (session.getAttribute("logiran_user") == null)) {
			session.setAttribute("anonimni", new User()); 
		}

		List<Item_category_db> categoryList = buildListKategorije(db);
		List<Item_subcategory_db> subcategoryList = buildListSubkategorije(db);
		List<Item_db> itemList = buildItemList(db, request, session);

		String subcategory_id = request.getParameter("subcategory_id");
		if (subcategory_id == null) {

			adress = "home.jsp?subcategory_id=-1";
		} else {
			adress = "home.jsp?subcategory_id=" + subcategory_id;
		}

		if (categoryList != null || subcategoryList != null || itemList != null) {
			session.setAttribute("kategorije", categoryList);
			session.setAttribute("subkategorije", subcategoryList);
			request.setAttribute("svi_proizvodi", itemList);
		}

		request.getRequestDispatcher(adress).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 
	 * @param db DatabaseConn objekt
	 * @return List<Item_category_db> - listu svih kategorija proizvoda iz baze
	 */
	private List<Item_category_db> buildListKategorije(DatabaseConn db) {
		try {
			db.connect();
		} catch (SQLException ex) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
		}

		try {
			ResultSet rs = db.querySELECT("SELECT * FROM item_category ORDER BY _id");

			List<Item_category_db> item_category_list = new ArrayList<>();

			while (rs.next()) {
				item_category_list.add(new Item_category_db(rs.getInt("_id"), rs.getString("category_name"),
						rs.getString("category_description")));

			}
			db.disconnect();
			return item_category_list;
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return null;
		}

	}

	/**
	 * 
	 * @param db DatabaseConn objekt za spajanje na bazu
	 * @return List<Item_subcategory_db> - listu svih pod kategorija proizvoda iz
	 *         baze
	 */
	private List<Item_subcategory_db> buildListSubkategorije(DatabaseConn db) {
		try {
			db.connect();
		} catch (SQLException ex) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
		}

		try {
			ResultSet rs = db.querySELECT("SELECT * FROM item_subcategory ORDER BY _id");

			List<Item_subcategory_db> item_subcategory_list = new ArrayList<>();

			while (rs.next()) {
				item_subcategory_list.add(new Item_subcategory_db(rs.getInt("_id"), rs.getInt("item_category_id"),
						rs.getString("subcategory_name"), rs.getString("subcategory_description")));

			}
			db.disconnect();
			return item_subcategory_list;
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return null;
		}

	}

	/**
	 * 
	 * @param db
	 * @param request
	 * @param session
	 * @return
	 */
	private List<Item_db> buildItemList(DatabaseConn db, HttpServletRequest request, HttpSession session) {
		List<Item_db> itemList = new ArrayList<>();
		String sort = request.getParameter("sortiranje_proizvoda");
		int sortiranje = 0;
		int subcategory_id = 0;
		
		if (sort!=null) {
			sortiranje = Integer.parseInt(sort);
		}
		
		
		String queryString = "SELECT * FROM item ";
		String s = request.getParameter("subcategory_id");
		
		if (s != null) {
			subcategory_id = Integer.parseInt(s);
		}

		if (subcategory_id > 0) {
			queryString += " WHERE item_subcategory_id = " + subcategory_id + " ";

		}
		
		if (sortiranje>0) {
			
			switch (sortiranje) {
			case 2:
				queryString += " ORDER BY item_name DESC";
				break;
			case 3:
				queryString += " ORDER BY item_price ";
				break;
			case 4:
				queryString += " ORDER BY item_price DESC";
				break;
			default:
				queryString += " ORDER BY item_name";
				break;
			}
			
			
		}
		
		

		try {
			db.connect();
			ResultSet rs = db.querySELECT(queryString);

			while (rs.next()) {

				itemList.add(new Item_db(rs.getInt("_id"), rs.getString("item_name"), rs.getInt("item_category_id"),
						rs.getInt("item_subcategory_id"), rs.getString("item_description"), rs.getInt("item_onstock"),
						rs.getInt("item_price")));
			}
			rs.close();
			db.disconnect();

		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return itemList;
	}

}
