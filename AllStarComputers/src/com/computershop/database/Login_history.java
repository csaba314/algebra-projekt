package com.computershop.database;

import java.io.Serializable;

public class Login_history implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private int user_id;
	private String login_timestamp;
	private String session_id;
	private String logout_timestamp;
	private User user;

	public Login_history(int _id, int user_id, String login_timestamp, String session_id, String logout_timestamp) {
		super();
		this._id = _id;
		this.user_id = user_id;
		this.login_timestamp = login_timestamp;
		this.session_id = session_id;
		this.logout_timestamp = logout_timestamp;
		this.user = null;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getLogin_timestamp() {
		return login_timestamp;
	}

	public void setLogin_timestamp(String login_timestamp) {
		this.login_timestamp = login_timestamp;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getLogout_timestamp() {
		return logout_timestamp;
	}

	public void setLogout_timestamp(String logout_timestamp) {
		this.logout_timestamp = logout_timestamp;
	}

}
