package com.mytest.DAO;

import java.util.ArrayList;

import com.mytest.DTO.FileDTO;

public interface FileDAO {
	
	public ArrayList<FileDTO> getFile();

	public void insertFile(FileDTO file);

}