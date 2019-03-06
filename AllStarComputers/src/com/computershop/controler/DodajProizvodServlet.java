package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Item_subcategory_db;

/**
 * Servlet implementation class DodajProizvodServlet
 */
@WebServlet("/DodajProizvodServlet")
public class DodajProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DodajProizvodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if ((request.getParameter("id_podkategorije") == null) || (request.getParameter("cijena_novog_proizv") == null)
				|| (request.getParameter("kolicina_novog_proizv") == null)) {
			session.removeAttribute("proizv_postoji");
			session.removeAttribute("uspjesno_dodano");
			response.sendRedirect("dodajProizvod.jsp");
			return;
		}

		int id_podkat = Integer.parseInt(request.getParameter("id_podkategorije"));
		String ime_proizvoda =  new String(request.getParameter("ime_novog_proizv").getBytes("ISO-8859-1"), "UTF-8");
		String opis_proizvoda = new String(request.getParameter("opis_proizvoda").getBytes("ISO-8859-1"), "UTF-8");
		int cijena_proiz = Integer.parseInt(request.getParameter("cijena_novog_proizv"));
		int kol_proiz = Integer.parseInt(request.getParameter("kolicina_novog_proizv"));

		if (isInDb(ime_proizvoda)) {
			session.setAttribute("proizv_postoji", "Proizvod već postoji");
			session.removeAttribute("uspjesno_dodano");
			response.sendRedirect("dodajProizvod.jsp");
			return;
		}

		int id_kategorije = 0;
		List<Item_subcategory_db> podkategorije = (ArrayList<Item_subcategory_db>) session.getAttribute("subkategorije");
		if (podkategorije != null) {
			for (Item_subcategory_db sub : podkategorije) {
				if (sub.get_id() == id_podkat) {
					id_kategorije = sub.getCategory_id();
				}
			}
		}

		DatabaseConn db = new DatabaseConn();
		String query = "INSERT INTO item (item_name, item_category_id, item_subcategory_id, item_description, item_onstock, item_price) "
				+ " VALUES ('" + ime_proizvoda + "', " + id_kategorije + ", " + id_podkat + ",'" + opis_proizvoda
				+ "', " + kol_proiz + ", " + cijena_proiz + ")";

		try {
			db.connect();
			db.queryUPDATE(query);
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.removeAttribute("proizv_postoji");
		session.setAttribute("uspjesno_dodano", "Proizvod " + ime_proizvoda + " uspješno dodan");
		response.sendRedirect("dodajProizvod.jsp");
	}

	private boolean isInDb(String ime_proizvoda) {
		DatabaseConn db = new DatabaseConn();
		String s = "zzz";

		try {
			db.connect();
			ResultSet rs = db.querySELECT("SELECT * FROM item WHERE item_name = '" + ime_proizvoda + "'");
			if (rs.next()) {
				s = rs.getString("item_name");
			}
			rs.close();
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (s.equals("zzz")) {
			return false;
		}
		return true;
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
