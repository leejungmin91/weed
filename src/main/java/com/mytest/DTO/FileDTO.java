package com.mytest.DTO;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO implements Serializable{
	private static final long serialVersionUID = -7667147479819193393L;
	private String filename;
	private long filePK;
	private String roomPK;
	private long filesize;
	private String reg_date;
	private String ran_filename;
	private MultipartFile uploadfile;
	
	public String getFileReg_date() {
		return reg_date;
	}

	public void setFileReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getFilePK() {
		return filePK;
	}

	public void setFilePK(long filePK) {
		this.filePK = filePK;
	}
	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}
	public String getRanFileName() {
		return ran_filename;
	}

	public void setRanFileRanName(String ran_filename) {
		this.ran_filename =ran_filename;
	}
	public String getFileRoomPK() {
		return roomPK;
	}

	public void setFileRoomPK(String roomPK) {
		this.roomPK = roomPK;
	}

	public long getFileSize() {
		return filesize;
	}

	public void setFileSize(long filesize) {
		this.filesize = filesize;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

}
