package com.mytest.DTO;

import java.io.Serializable;

public class MessageDTO implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;
	private String reg_date;
	private String send_fb_id;
	private String recv_fb_id;
	private String roomPK;

	public String getMessageReg_Date() {
		return reg_date;
	}

	public void setMessageReg_Date(String reg_date) {
		this.reg_date = reg_date;
	}


	public String getMessageRecvFb_id() {
		return recv_fb_id;
	}

	public void setMessageRecvFb_id(String recv_fb_id) {
		this.recv_fb_id = recv_fb_id;
	}
	public String getMessageSendFb_id() {
		return send_fb_id;
	}

	public void setMessageSendFb_id(String send_fb_id) {
		this.send_fb_id = send_fb_id;
	}
	
	public String getMessageRoomPK() {
		return roomPK;
	}

	public void setMessageRoomPK(String roomPK) {
		this.roomPK = roomPK;
	}
}
