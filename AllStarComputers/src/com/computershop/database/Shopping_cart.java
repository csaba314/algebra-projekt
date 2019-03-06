package com.computershop.database;

import java.io.Serializable;

public class Shopping_cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private int user_id;
	private int item_id;
	private int item_amount;

	private Item_db itemObject;

	public Shopping_cart(int _id, int user_id, int item_id, int item_amount) {
		super();
		this._id = _id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_amount = item_amount;
		this.itemObject = null;

	}

	public Item_db getItemObject() {
		return itemObject;
	}

	public void setItemObject(Item_db itemObject) {
		this.itemObject = itemObject;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_amount() {
		return item_amount;
	}

	public void setItem_amount(int item_amount) {
		this.item_amount = item_amount;
	}

}
