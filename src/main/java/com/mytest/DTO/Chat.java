package com.mytest.DTO;

import java.io.Serializable;

public class Chat implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;
	private String reg_data;
	private String content;
	private String username;
	private String roomname;
	private String roomPK;

	public String getChatReg_Data() {
		return reg_data;
	}

	public void setChatReg_Data(String reg_data) {
		this.reg_data = reg_data;
	}

	public String getChatContent() {
		return content;
	}

	public void setChatContent(String content) {
		this.content = content;
	}

	public String getChatUserName() {
		return username;
	}

	public void setChatUserName(String username) {
		this.username = username;
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
