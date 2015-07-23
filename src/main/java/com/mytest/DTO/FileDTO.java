package com.mytest.DTO;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	private String filename;
	private long filesize;
	private MultipartFile uploadfile;

	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
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
