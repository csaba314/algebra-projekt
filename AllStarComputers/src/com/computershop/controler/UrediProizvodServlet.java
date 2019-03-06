package com.computershop.controler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Item_db;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UrediProizvodServlet
 */
@WebServlet("/UrediProizvodServlet")
public class UrediProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UrediProizvodServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		          HttpSession session = request.getSession();
		if (request.getParameter("uredi_proizv_id") ==null) {
			response.sendRedirect("ControlerServlet");
			return;
		}
		
		int proizv_id = Integer.parseInt(request.getParameter("uredi_proizv_id"));
		
		DatabaseConn db = new DatabaseConn();
		Item_db proizv = null;
		
		try {
			db.connect();
			ResultSet rs =  db.querySELECT("SELECT * FROM item WHERE _id=" +proizv_id);
			
			if (rs.next()) {
				proizv = new Item_db(rs.getInt("_id"), rs.getString("item_name"), rs.getInt("item_category_id"),
						rs.getInt("item_subcategory_id"), rs.getString("item_description"), rs.getInt("item_onstock"),
						rs.getInt("item_price"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (proizv!=null) {
                    session.setAttribute("proizvod", proizv);
		}
		
		request.getRequestDispatcher("urediProizvod.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
