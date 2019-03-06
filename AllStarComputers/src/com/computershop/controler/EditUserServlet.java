package com.computershop.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.DatabaseConn;
import com.computershop.database.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("logiran_user");
		
		String ime =  new String(request.getParameter("first_name_reg").getBytes("ISO-8859-1"), "UTF-8");
		String prezime =  new String(request.getParameter("last_name_reg").getBytes("ISO-8859-1"), "UTF-8");
		String email =  new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
		String pass =  new String(request.getParameter("passwd").getBytes("ISO-8859-1"), "UTF-8");
		String adresa =  new String(request.getParameter("adresa_reg").getBytes("ISO-8859-1"), "UTF-8");
		String mjesto =  new String(request.getParameter("mjesto_reg").getBytes("ISO-8859-1"), "UTF-8");
		int nacin_placanja = Integer.parseInt(request.getParameter("nacin_placanja"));
		String punaAdresa =  adresa + " --- " + mjesto;
		
		user1.setFirstName(ime);
		user1.setLastName(prezime);
		user1.setEmail(email);
		user1.setPassword(pass);
		user1.setAdresa(punaAdresa);
		user1.setNacin_placanja_id(nacin_placanja);
		
		DatabaseConn db = new DatabaseConn();
		
		session.setAttribute("logiran_user", user1);
		
		String query = "UPDATE user_ SET firstName='" + ime + "', lastName = '" + prezime 
				+ "', adresa = '" +  punaAdresa + "', email = '" + email 
				+ "', password = '" + pass + "', nacin_placanja_id = " + nacin_placanja 
				+ " WHERE _id = " + user1.get_id(); 
				
		
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
		
		response.sendRedirect("ControlerServlet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
