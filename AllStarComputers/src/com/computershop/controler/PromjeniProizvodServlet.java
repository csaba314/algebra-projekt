package com.computershop.controler;

import com.computershop.database.DatabaseConn;
import com.computershop.database.Item_subcategory_db;
import java.io.IOException;
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
import javax.servlet.jsp.PageContext;

/**
 * Servlet implementation class PromjeniProizvodServlet
 */
@WebServlet("/PromjeniProizvodServlet")
public class PromjeniProizvodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromjeniProizvodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DatabaseConn db = new DatabaseConn();
        String id_kategorije = null;
        String id_proizvoda = request.getParameter("id_proizvoda");
        String id_podkategorije = request.getParameter("id_podkategorije");
        String ime_proizvoda =  new String(request.getParameter("ime_novog_proizv").getBytes("ISO-8859-1"), "UTF-8");
        String opis_proizvoda =  new String(request.getParameter("opis_proizvoda").getBytes("ISO-8859-1"), "UTF-8");
        String cijena_proizvoda = request.getParameter("cijena_novog_proizv");
        String kolicina_proizvoda = request.getParameter("kolicina_novog_proizv");
        int id_kat = 0; 
        if ((id_proizvoda == null) || (id_podkategorije == null) || (ime_proizvoda == null)
                || (opis_proizvoda == null) || (cijena_proizvoda == null) || (kolicina_proizvoda == null)) {
            response.sendRedirect("ControlerServlet");
            return;
        }
        int id_podkat = Integer.parseInt(id_podkategorije);
        List<Item_subcategory_db> podkatLista = (ArrayList<Item_subcategory_db>) session.getAttribute("subkategorije");
        if (podkatLista != null) {
            for (Item_subcategory_db sub : podkatLista) {
                if (id_podkat==sub.get_id()) {
                    id_kat = sub.getCategory_id();
                    System.out.println("u for petlji ulaz u IF id_kategorije= " + id_kat);
                }
            }
        }       
        int ci_pro = Integer.parseInt(cijena_proizvoda);
        int pro_id = Integer.parseInt(id_proizvoda);
        System.out.println("id proizvoda: " + pro_id);
        int kol_pro = Integer.parseInt(kolicina_proizvoda);
        String query = "UPDATE item "
                + "SET "
                + " item_name='" + ime_proizvoda + "', "
                + " item_category_id = " + id_kat + ", "
                + " item_subcategory_id = " + id_podkat + ", "
                + " item_description = '" + opis_proizvoda + "', "
                + " item_onstock = " + kol_pro + ", "
                + " item_price = " + ci_pro + " "
                + " WHERE _id = " + pro_id;

        try {
            db.connect();
            db.queryUPDATE(query);
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(PromjeniProizvodServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PromjeniProizvodServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("ControlerServlet");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
