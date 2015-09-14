package com.mytest.DTO;

import java.io.Serializable;

public class RoomUserDTO implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;

	private String fb_id;
	private String roomPK;
	private String reg_date;
	private String roomname;

	public String getRoomUserFb_id() {
		return fb_id;
	}

	public void setRoomUserFb_id(String fb_id) {
		this.fb_id = fb_id;
	}
	
	/*public String getUserReg_date() {
		return reg_date;
	}

	public void setUserReg_date(String reg_date) {
		this.reg_date = reg_date;
	}*/
	public String getRoomUserName() {
		return roomname;
	}

	public void setRoomUserName(String roomname) {
		this.roomname = roomname;
	}
	
	public String getRoomUserPK() {
		return roomPK;
	}

	public void setRoomUserPK(String roomPK) {
		this.roomPK = roomPK;
	}
}
