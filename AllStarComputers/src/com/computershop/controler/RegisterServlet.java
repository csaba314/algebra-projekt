/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.User;

/**
 *
 * @author programer
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("anonimni") == null) {
			response.sendRedirect("ControlerServlet");
			return;
		}

		String ime =  new String(request.getParameter("first_name_reg").getBytes("ISO-8859-1"), "UTF-8");
		String prezime =  new String(request.getParameter("last_name_reg").getBytes("ISO-8859-1"), "UTF-8");
		String email =  new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
		String pass =  new String(request.getParameter("passwd").getBytes("ISO-8859-1"), "UTF-8");
		String adresa =  new String(request.getParameter("adresa_reg").getBytes("ISO-8859-1"), "UTF-8");
		String mjesto =  new String(request.getParameter("mjesto_reg").getBytes("ISO-8859-1"), "UTF-8");

		if ((ime == null) || (prezime == null) || (email == null) || (pass == null)) {
			response.sendRedirect("register.jsp");
			return;
		}

		String punaAdresa ="";
		if (adresa == null || mjesto == null) {
			punaAdresa = "---";
		} else {
			punaAdresa =  adresa + " --- " + mjesto;
		}
		
		

		User user1 = new User(-1, ime, prezime, punaAdresa, email, pass, 1);

		if (isMailRegistered(email)) {
			response.sendRedirect("register.jsp?msg=1");
			session.setAttribute("error_mail", "Email adresa je već u bazi. Pokušaj ponovo");
			return;
		}

		session.setAttribute("logiran_user", user1);

		session.removeAttribute("anonimni");
		
		dodajUseraBaza(user1);
		user1.set_id(dohvati_idBaza(email));
		response.sendRedirect("ControlerServlet");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

	/**
	 * 
	 * @param user
	 */
	private void dodajUseraBaza(User user) {
		DatabaseConn db = new DatabaseConn();
		String query = "INSERT INTO user_ (firstName, lastName, adresa, email, password, user_level_id, nacin_placanja_id) "
				+ " VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getAdresa() + "', '"
				+ user.getEmail() + "', '" + user.getPassword() + "', 1, 1)";

		try {
			db.connect();
			db.queryUPDATE(query);
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	private boolean isMailRegistered(String email) {
		DatabaseConn db = new DatabaseConn();
		String query = "SELECT * FROM user_ WHERE email = '" + email + "'";

		try {
			db.connect();
			ResultSet rs = db.querySELECT(query);
			if (rs.next()) {
				return true;
			}

			rs.close();
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private int dohvati_idBaza(String email) {
		DatabaseConn db = new DatabaseConn();
		String query = "SELECT _id FROM user_ WHERE email = '" + email + "'";
		int _id = 0;
		try {
			db.connect();
			ResultSet rs = db.querySELECT(query);
			if (rs.next()) {
				_id = rs.getInt("_id");
			}
			rs.close();
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return _id;
	}

}
