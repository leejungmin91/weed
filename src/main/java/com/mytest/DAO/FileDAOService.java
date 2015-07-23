package com.mytest.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mytest.DTO.FileDTO;
@Repository
public class FileDAOService implements FileDAO{
	private static final Logger logger = LoggerFactory.getLogger(FileDAOService.class);
	@Autowired
	private SqlSession sqlSession;

	public ArrayList<FileDTO> getFile() {
		// db에서 email로 체크하는부분
		ArrayList<FileDTO> result = new ArrayList<FileDTO>();
		// sqlSession을 통하여 매핑한다.
		logger.info("fileDAOService.java");
		FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
		result = fileMapper.getFile();
		//logger.info("getFile after"+result.get(0).getFileName());
		// getfile()의 메소드명과 mapper.mxl과 id는 동일해야한다.
		return result;

	}

	public void insertFile(FileDTO file) {
		logger.info("insertfile");
		FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
		fileMapper.insertFile(file);

	}

}
