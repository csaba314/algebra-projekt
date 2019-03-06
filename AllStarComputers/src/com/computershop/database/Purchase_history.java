package com.computershop.database;

import java.io.Serializable;

public class Purchase_history implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private int item_id;
	private int amount;
	private int user_id;
	private String purchase_timestamp;
	private int nacin_placanja_id;
	private int item_price;
	private String nacin_placanja_str;
	private Item_db item_object;

	public Purchase_history(int _id, int item_id, int amount, int item_price, int user_id, String purchase_timestamp,
			int nacin_placanja_id) {
		super();
		this._id = _id;
		this.item_id = item_id;
		this.amount = amount;
		this.item_price = item_price;
		this.user_id = user_id;
		this.purchase_timestamp = purchase_timestamp;
		this.nacin_placanja_id = nacin_placanja_id;
		item_object = null;

		switch (nacin_placanja_id) {
		case 1:
			this.nacin_placanja_str = "Pay-Pal";
			break;
		case 2:
			this.nacin_placanja_str = "Pouzece";
			break;
		case 3:
			this.nacin_placanja_str = "Virman";
			break;
		case 4:
			this.nacin_placanja_str = "VISA";
			break;
		case 5:
			this.nacin_placanja_str = "Mastercard";
			break;
		default:
			this.nacin_placanja_str = "Pay-Pal";
			break;
		}

	}

	public Item_db getItem_object() {
		return item_object;
	}

	public void setItem_object(Item_db item_object) {
		this.item_object = item_object;
	}

	public String getNacin_placanja_str() {
		return nacin_placanja_str;
	}

	public void setNacin_placanja_str(String nacin_placanja_str) {
		this.nacin_placanja_str = nacin_placanja_str;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPurchase_timestamp() {
		return purchase_timestamp;
	}

	public void setPurchase_timestamp(String purchase_timestamp) {
		this.purchase_timestamp = purchase_timestamp;
	}

	public int getNacin_placanja_id() {
		return nacin_placanja_id;
	}

	public void setNacin_placanja_id(int nacin_placanja_id) {
		this.nacin_placanja_id = nacin_placanja_id;
	}

}
