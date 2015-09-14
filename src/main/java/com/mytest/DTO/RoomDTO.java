package com.mytest.DTO;

import java.io.Serializable;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = -7667147479819193393L;
	private String reg_date;
	private String roomname;
	private String roomPK;
	private String socketId;
	
	public String getRoomReg_Date() {
		return reg_date;
	}

	public void setRoomReg_Date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getRoomName() {
		return roomname;
	}

	public void setRoomName(String roomname) {
		this.roomname = roomname;
	}
	public String getRoomPK() {
		return roomPK;
	}

	public void setRoomPK(String roomPK) {
		this.roomPK = roomPK;
	}
}
