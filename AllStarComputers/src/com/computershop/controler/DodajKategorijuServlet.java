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
import com.computershop.database.Item_subcategory_db;


@WebServlet("/DodajKategorijuServlet")
public class DodajKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public DodajKategorijuServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idKategorije = Integer.parseInt(request.getParameter("id_kategorije"));
		String ime_kategorije = new String((request.getParameter("ime_nove_podkategorije").getBytes("ISO-8859-1")), "UTF-8");
		String opis_kat = new String(request.getParameter("opis_nove_podkategorije").getBytes("ISO-8859-1"), "UTF-8");
		int id_podkat=0;
		String itemMsg = "";
		
		if (isInDb(ime_kategorije)) {
			
			response.sendRedirect("dodajProizvod.jsp");
			return;
		}	
		List<Item_subcategory_db> subcategoryList = (ArrayList<Item_subcategory_db>) session.getAttribute("subkategorije");

		DatabaseConn db = new DatabaseConn();
		String query = "INSERT INTO item_subcategory (item_category_id, subcategory_name, subcategory_description) "
				+ " VALUES (" + idKategorije + ", '" + ime_kategorije + "', '" + opis_kat + "')";
		
		try {
			db.connect();
			db.queryUPDATE(query);
			query = "SELECT _id FROM item_subcategory WHERE subcategory_name = '" + ime_kategorije + "'";
			ResultSet rs = db.querySELECT(query);
			if (rs.next()) {
				id_podkat = rs.getInt("_id");
			}
			
			rs.close();
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		subcategoryList.add(new Item_subcategory_db(id_podkat, idKategorije, ime_kategorije, opis_kat));
		session.setAttribute("subkategorije", subcategoryList);
		session.setAttribute("newItemMsg", "Nova podkategorija je uspješno dodana");
		response.sendRedirect("dodajProizvod.jsp");
	}

	private boolean isInDb(String ime_kategorije) {
		DatabaseConn db = new DatabaseConn();
		String s = "zzz";
		
		try {
			db.connect();
			ResultSet rs =  db.querySELECT("SELECT * FROM item_subcategory WHERE subcategory_name = '" + ime_kategorije+"'");
			if (rs.next()) {
				s = rs.getString("subcategory_name");
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
