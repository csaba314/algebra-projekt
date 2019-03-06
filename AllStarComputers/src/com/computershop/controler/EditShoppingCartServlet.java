package com.computershop.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computershop.database.Shopping_cart;

@WebServlet("/EditShoppingCartServlet")
public class EditShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditShoppingCartServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Shopping_cart> shoppingCart = (ArrayList<Shopping_cart>) session.getAttribute("shopping_cart");
		int ukupno = 0;
		String prom_kol = request.getParameter("promjeni_kolicinu_kos");
		String prom_kol_item_id = request.getParameter("promjeni_kol_item_id");
		
		int novaKolicinaKos = 0;
		int promj_item_id=0;
		
		if (prom_kol_item_id!=null) {
			promj_item_id = Integer.parseInt(prom_kol_item_id);
		}
		if (prom_kol!=null) {
			novaKolicinaKos = Integer.parseInt(prom_kol);
			
		}
		
		if ((novaKolicinaKos > 0) && (promj_item_id > 0)) {
			shoppingCart = promjeniKolicinu(novaKolicinaKos, promj_item_id, shoppingCart);
		}
		
		String s =  request.getParameter("ukloni_item_id");
		int ukloni_item_id = 0;
		if (s != null) {
			ukloni_item_id = Integer.parseInt(s);
		}

		if (ukloni_item_id > 0) {
			Shopping_cart sc = null;
			for (int i = 0; i < shoppingCart.size(); i++) {
				sc = shoppingCart.get(i);
				if (sc.getItem_id() == ukloni_item_id) {
					shoppingCart.remove(i);
				}
			}
			session.setAttribute("brojProizvodaKosarica", shoppingCart.size());
		}
		
		if (shoppingCart != null) {
			for (Shopping_cart cart : shoppingCart) {
				ukupno += (cart.getItem_amount() * cart.getItemObject().getItem_price());
			}
		}
		
		session.setAttribute("shopping_cart", shoppingCart);
		session.setAttribute("ukupno_novaca", ukupno);
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
		
		
		
	}

	private List<Shopping_cart> promjeniKolicinu(int novaKolicinaKos, int prom_kol_item_id, List<Shopping_cart> shoppingCart) {
			for (Shopping_cart sc : shoppingCart) {
				if (sc.getItem_id() == prom_kol_item_id) {
					sc.setItem_amount(novaKolicinaKos);
				}
			}
			return shoppingCart;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
