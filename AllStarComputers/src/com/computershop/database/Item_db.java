/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computershop.database;

import java.io.Serializable;

/**
 *
 * @author programer
 */
public class Item_db implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private String item_name;
	private int item_category_id;
	private int item_subcategory_id;
	private String item_description;
	private int item_onstock;
	private int item_price;

	private String item_category;
	private String item_subcategory;

	public Item_db(int _id, String item_name, int item_category_id, int item_subcategory_id, String item_description,
			int item_onstock, int item_price) {
		this._id = _id;
		this.item_name = item_name;
		this.item_category_id = item_category_id;
		this.item_subcategory_id = item_subcategory_id;
		this.item_description = item_description;
		this.item_onstock = item_onstock;
		this.item_price = item_price;

		switch (item_category_id) {
		case 1:
			this.item_category = "Stolno računalo";
			break;
		case 2:
			this.item_category = "Prijenosno računalo";
			break;
		case 3:
			this.item_category = "Komponente";
			break;
		case 4:
			this.item_category = "Smartphone";
			break;
		default:
			this.item_category = "Ostalo";
		}
		switch (item_subcategory_id) {
		case 1:
			this.item_subcategory = "Laptop";
			break;
		case 2:
			this.item_subcategory = "Tablet";
			break;
		case 3:
			this.item_subcategory = "Procesor";
			break;
		case 4:
			this.item_subcategory = "Grafička kartica";
			break;
		case 5:
			this.item_subcategory = "SSD";
			break;
		case 6:
			this.item_subcategory = "HDD";
			break;
		case 7:
			this.item_subcategory = "Kućište";
			break;
		case 8:
			this.item_subcategory = "Matična ploča";
			break;
		case 9:
			this.item_subcategory = "RAM";
			break;

		case 11:
			this.item_subcategory = "Napajanje";
			break;
		case 12:
			this.item_subcategory = "Ostalo";
			break;
		case 13:
			this.item_subcategory = "Gotova konfiguracija";
			break;
		case 14:
			this.item_subcategory = "Smartphone";
			break;
		default:
			this.item_subcategory = "Ostalo";
		}

	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_category_id() {
		return item_category_id;
	}

	public void setItem_category_id(int item_category_id) {
		this.item_category_id = item_category_id;
	}

	public int getItem_subcategory_id() {
		return item_subcategory_id;
	}

	public void setItem_subcategory_id(int item_subcategory_id) {
		this.item_subcategory_id = item_subcategory_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public int getItem_onstock() {
		return item_onstock;
	}

	public void setItem_onstock(int item_onstock) {
		this.item_onstock = item_onstock;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public String getItem_subcategory() {
		return item_subcategory;
	}

	public void setItem_subcategory(String item_subcategory) {
		this.item_subcategory = item_subcategory;
	}

}
