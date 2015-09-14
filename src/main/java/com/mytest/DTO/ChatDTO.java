package com.mytest.DTO;

import java.io.Serializable;

public class ChatDTO implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;
	private String reg_date;
	private String content;
	private String fb_id;
	private String roomname;
	private String roomPK;

	public String getChatReg_Date() {
		return reg_date;
	}

	public void setChatReg_Date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getChatContent() {
		return content;
	}

	public void setChatContent(String content) {
		this.content = content;
	}

	public String getChatFb_id() {
		return fb_id;
	}

	public void setChatFb_id(String fb_id) {
		this.fb_id = fb_id;
	}
	
	public String getChatRoomName() {
		return roomname;
	}

	public void setChatRoomName(String roomname) {
		this.roomname = roomname;
	}
	public String getChatRoomPK() {
		return roomPK;
	}

	public void setChatRoomPK(String roomPK) {
		this.roomPK = roomPK;
	}
}
