package com.mytest.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;
	private String gender;
	private String username;
	private String fb_id;
	private String roomPK;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserFb_id() {
		return fb_id;
	}

	public void setUserFb_id(String fb_id) {
		this.fb_id = fb_id;
	}
}
