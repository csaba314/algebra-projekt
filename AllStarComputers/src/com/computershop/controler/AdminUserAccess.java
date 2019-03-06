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
import com.computershop.database.User;


@WebServlet("/AdminUserAccess")
public class AdminUserAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminUserAccess() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<User> userList = new ArrayList<>();
		int akcija = 0;
		int korisnik_id=0;
		
		if (session.getAttribute("logiran_user") !=null) {
			User tempUser = (User) session.getAttribute("logiran_user");
			
			if (tempUser.getUser_level_id() != 2) {
				response.sendRedirect("ControlerServlet");
			}
		}
		String s = request.getParameter("promjena_korisnika");
		String s2 = request.getParameter("korisnik_id");	
		
		if (s!=null && s2!=null) {
			akcija = Integer.parseInt(s);
			korisnik_id = Integer.parseInt(s2);
		}
		
		if (akcija>0) {
			switch (akcija) {
			case 1:
				postaviUserLevel(1, korisnik_id);
				break;
			case 2:
				postaviUserLevel(2, korisnik_id);
				break;
			case 3:
				postaviUserLevel(3, korisnik_id);
				deaktivirajKorisnika(korisnik_id);
				break;
			
			case 4:
				resetirajPassword(korisnik_id);
				postaviUserLevel(1, korisnik_id);
				break;
			}
		}
		userList = dohvatiListuKorisnika();
		session.setAttribute("lista_korisnika", userList);
		response.sendRedirect("adminUserPannel.jsp");
	}

	
	private void resetirajPassword(int korisnik_id) {
		DatabaseConn db = new DatabaseConn();
		String newPass = "password";
		
		String query = "UPDATE user_ SET  password = '" + newPass+ "' WHERE _id= " + korisnik_id;
		
		try {
			db.connect();
			db.queryUPDATE(query);
			db.disconnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void deaktivirajKorisnika(int korisnik_id) {
		DatabaseConn db = new DatabaseConn();
		String newPass = "";
		
		String query = "UPDATE user_ SET  password = '" + newPass+ "' WHERE _id= " + korisnik_id;
		
		try {
			db.connect();
			db.queryUPDATE(query);
			db.disconnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void postaviUserLevel(int userLevel, int korisnik_id) {
		DatabaseConn db = new DatabaseConn();
		String query = "UPDATE user_ SET user_level_id = " + userLevel
				+ " WHERE _id= " + korisnik_id;
		
		try {
			db.connect();
			db.queryUPDATE(query);
			db.disconnect();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	private List<User> dohvatiListuKorisnika() {
		
		List<User> userList = new ArrayList<>();

		DatabaseConn db = new DatabaseConn();
		String query = "SELECT * FROM user_ ORDER BY _id";
		
		try {
			db.connect();
			ResultSet rs = db.querySELECT(query);
			
			while (rs.next()) {
				userList.add(new User(
						rs.getInt("_id"), 
						rs.getString("firstName"), 
						rs.getString("lastName"), 
						rs.getString("adresa"), 
						rs.getString("email"), 
						rs.getString("password"), 
						rs.getInt("user_level_id")));
			}
			rs.close();
			db.disconnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
