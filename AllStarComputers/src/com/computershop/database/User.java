package com.computershop.database;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private String firstName;
	private String lastName;
	private String adresa;
	private String email;
	private String password;
	private int user_level_id;
	private int nacin_placanja_id;

	private String user_level;

	public User(int _id, String firstName, String lastName, String adresa, String email, String password,
			int user_level_id) {
		super();
		this._id = _id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresa = adresa;
		this.email = email;
		this.password = password;
		this.user_level_id = user_level_id;
		this.nacin_placanja_id = 1;
		if (user_level_id == 3) {
			this.user_level = "Neaktivan";
		} else if (user_level_id == 2) {
			this.user_level = "Administrator";
		} else {
			this.user_level = "Korisnik";
		}
	}

	public User() {
		this(-1, "Anonimni", "korisnik", "unknown", "unknown", "", 0);
		this.nacin_placanja_id = 1;
		this.user_level = "Anonimni";
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_level_id() {
		return user_level_id;
	}

	public void setUser_level_id(int user_level_id) {
		this.user_level_id = user_level_id;
	}

	public int getNacin_placanja_id() {
		return nacin_placanja_id;
	}

	public void setNacin_placanja_id(int nacin_placanja_id) {
		this.nacin_placanja_id = nacin_placanja_id;
	}

}
