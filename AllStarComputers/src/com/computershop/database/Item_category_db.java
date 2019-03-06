package com.computershop.database;

import java.io.Serializable;

public class Item_category_db implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private String category_name;
	private String category_description;
	
	public Item_category_db(int _id, String category_name, String category_description) {
		super();
		this._id = _id;
		this.category_name = category_name;
		this.category_description = category_description;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	
	
	
	

}
