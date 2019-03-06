package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Item_db;
import com.computershop.database.Shopping_cart;
import com.computershop.database.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user;

		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		DatabaseConn db = new DatabaseConn();

		if (email != null && pass != null) {
			user = getUserFromDb(db, email, pass);
			if (user != null) {
				session.setAttribute("logiran_user", user);

				createLoginEntry(db, session, user);
				updateShoppingCart(db, session, user);
				removeShoppingCartBackup(db, user);

				session.removeAttribute("anonimni");
				response.sendRedirect("ControlerServlet?subcategory_id=-1");
				return;
			}
		}

		if (session.getAttribute("logiran_user") != null) {
			response.sendRedirect("ControlerServlet");///// promjena sa login.jsp
			return;
		}

		response.sendRedirect("ControlerServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private User getUserFromDb(DatabaseConn db, String email, String pass) {
		User user = null;

		
		try {
			db.connect();
			ResultSet rs = db.querySELECT("SELECT * FROM user_ " + " WHERE email = '" + email + "' " + " ORDER BY _id");

			if (rs.next()) {
				int _id = rs.getInt("_id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String adresa = rs.getString("adresa");
				String _email = rs.getString("email");
				String _password = rs.getString("password");
				int user_level_id = rs.getInt("user_level_id");
				int nacin_placanja_id = rs.getInt("nacin_placanja_id");

				if (pass.equals(_password)) {
					user = new User(_id, firstName, lastName, adresa, _email, _password, user_level_id);
					user.setNacin_placanja_id(nacin_placanja_id);
					

				}
			}
			rs.close();
			db.disconnect();

		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		
		return user;
	}

	private void createLoginEntry(DatabaseConn db, HttpSession session, User user) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String login_timestamp = dateFormat.format(date);

		try {
			db.connect();

			String query = "INSERT INTO login_history (user_id, login_timestamp, session_id, logout_timestamp)"
					+ " VALUES (" + user.get_id() + ", '" + login_timestamp + "', '" + session.getId() + "', " + -1
					+ ")";
			db.queryUPDATE(query);

			db.disconnect();
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void updateShoppingCart(DatabaseConn db, HttpSession session, User user) {

		List<Shopping_cart> dbshoppingCart = new ArrayList<Shopping_cart>();
		List<Shopping_cart> sessionShoppinCart;

		Object o = session.getAttribute("shopping_cart");
		if (o != null) {
			sessionShoppinCart = (ArrayList<Shopping_cart>) o;
		} else {
			sessionShoppinCart = new ArrayList<Shopping_cart>();
		}

		try {
			db.connect();

			ResultSet rs = db.querySELECT(
					"SELECT * FROM shopping_cart " + " WHERE user_id = " + user.get_id() + " ORDER BY _id");

			while (rs.next()) {
				int _id = rs.getInt("_id");
				int user_id = rs.getInt("user_id");
				int item_id = rs.getInt("item_id");
				int item_amount = rs.getInt("item_amount");

				dbshoppingCart.add(new Shopping_cart(_id, user_id, item_id, item_amount));
			}

			for (Shopping_cart current : dbshoppingCart) {
				rs = db.querySELECT("SELECT * FROM item WHERE _id= " + current.getItem_id());

				while (rs.next()) {
					current.setItemObject(new Item_db(rs.getInt("_id"), rs.getString("item_name"),
							rs.getInt("item_category_id"), rs.getInt("item_subcategory_id"),
							rs.getString("item_description"), rs.getInt("item_onstock"), rs.getInt("item_price")));
				}
			}

			// ako je lista (koja je popunjena iz baze) prazna, dodaj listu iz session
			if (dbshoppingCart.size() == 0) {
				dbshoppingCart = sessionShoppinCart;
			} else {
				// lista nije prazna, te moramo dodati podatke iz session kosarice
				/* ------- */ dbshoppingCart = mergeShoppingCarts(dbshoppingCart, sessionShoppinCart); //////////////////////////////
//				
			}

			// postavi session atribut na novu kosaricu
			session.setAttribute("shopping_cart", dbshoppingCart);
			session.setAttribute("brojProizvodaKosarica", dbshoppingCart.size());

			rs.close();
			db.disconnect();
		} catch (Exception e) {
			Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}

	}

	private void removeShoppingCartBackup(DatabaseConn db, User user) {

		try {
			db.connect();

			String query = "DELETE FROM shopping_cart where user_id = " + user.get_id();

			db.queryUPDATE(query);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private List<Shopping_cart> mergeShoppingCarts(List<Shopping_cart> dbshoppingCart,
			List<Shopping_cart> sessionShoppinCart) {

		if (dbshoppingCart.size() == 0) {
			return sessionShoppinCart;
		} else if (sessionShoppinCart.size() == 0) {
			return dbshoppingCart;
		} else {
			int amount = 0;
			Shopping_cart sc;

			for (Shopping_cart dbCart : dbshoppingCart) {

				for (int i = 0; i < sessionShoppinCart.size(); i++) {
					sc = sessionShoppinCart.get(i);
					if (sc == null) {
						continue;
					}

					// ako u obje liste ima proiuzvod sa istim item_id
					if (dbCart.getItem_id() == sc.getItem_id()) {
						// pokupi količinu proizvoda iz liste iz baze
						amount = dbCart.getItem_amount();
						// dodaj količinu iz liste iz session
						amount += sc.getItem_amount();
						// postavi novu vrijednost za količinu
						dbCart.setItem_amount(amount);

						// ukloni duplikat
						sessionShoppinCart.remove(i);

						// preskoči ostatak iteracije
						continue;
					}

					// ako ne uđe u if, znači da je proizvod drugačiji, te ga treba dodati na listu
					dbshoppingCart.add(sc);
				}
			}
			return dbshoppingCart;
		}
	}

}
