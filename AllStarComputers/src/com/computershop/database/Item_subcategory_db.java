package com.computershop.database;

import java.io.Serializable;

public class Item_subcategory_db implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private int category_id;
	private String subcategory_name;
	private String subcategory_description;

	public Item_subcategory_db(int _id, int category_id, String subcategory_name, String subcategory_description) {
		super();
		this._id = _id;
		this.category_id = category_id;
		this.subcategory_name = subcategory_name;
		this.subcategory_description = subcategory_description;
	}
	
	
	

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getSubcategory_name() {
		return subcategory_name;
	}

	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}

	public String getSubcategory_description() {
		return subcategory_description;
	}

	public void setSubcategory_description(String subcategory_description) {
		this.subcategory_description = subcategory_description;
	}

}
