package com.mytest.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.mytest.DTO.FileDTO;

@Repository
public class FileDAOImpl implements FileDAO {

  @Autowired
  MongoTemplate mongoTemplate;
  private static final Logger logger = LoggerFactory
			.getLogger(FileDAOImpl.class);
  private static String COLLECTION_NAME = "jabcho_file";

  @Override
  public FileDTO insert(FileDTO file) {
	logger.info("file insert");
    mongoTemplate.insert(file,COLLECTION_NAME);
    return file;
  }
  @Override
  public FileDTO getFileDAOPK(long filePK) {
	  Query query = new Query(Criteria.where("filePK").is(filePK));
	  return mongoTemplate.findOne(query, FileDTO.class, COLLECTION_NAME);
  }
  @Override
  public FileDTO getFileDAORan_Name(String ran_filename) {
	  Query query = new Query(Criteria.where("ran_filename").is(ran_filename));
	  return mongoTemplate.findOne(query, FileDTO.class, COLLECTION_NAME);
  }
  @Override
	public List<FileDTO> getFiles(String roomPK) {
		Query query = new Query(Criteria.where("roomPK").is(roomPK));
		return (List<FileDTO>) mongoTemplate.find(query,FileDTO.class , COLLECTION_NAME);
	}
  @Override
  public long getFilesNum() {
	   Query query = new Query();
	  return mongoTemplate.count(query, COLLECTION_NAME);
  }
  

  @Override
  public List<FileDTO> getFiles() {
    return (List<FileDTO>) mongoTemplate.findAll(FileDTO.class);
  }

  @Override
  public void deleteFile(FileDTO file) {
  }

  @Override
  public FileDTO updateFile(FileDTO file) {
    return file;
  }

}
