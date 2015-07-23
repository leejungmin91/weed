package com.mytest.DAO;

import java.util.ArrayList;

import com.mytest.DTO.FileDTO;

public interface FileMapper {

	ArrayList<FileDTO> getFile();

	void insertFile(FileDTO file);

}
