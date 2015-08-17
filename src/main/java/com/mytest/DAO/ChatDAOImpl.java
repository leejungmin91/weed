package com.mytest.DAO;

import com.mytest.DTO.Chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAOImpl implements ChatDAO {

  @Autowired
  MongoTemplate mongoTemplate;
  private static final Logger logger = LoggerFactory
			.getLogger(ChatDAOImpl.class);
  private static String COLLECTION_NAME = "jabcho_chat";

  @Override
  public Chat insert(Chat chat) {
	logger.info("chat insert");
    mongoTemplate.insert(chat,COLLECTION_NAME);
    return chat;
  }
  @Override
  public Chat getChatDAOReg_Date(String reg_date) {
	  Query query = new Query(Criteria.where("reg_date").is(reg_date));
	  return mongoTemplate.findOne(query, Chat.class, COLLECTION_NAME);
  }

  @Override
  public Chat getChatDAOContent(String content) {
	Query query = new Query(Criteria.where("content").is(content));
    return mongoTemplate.findOne(query, Chat.class, COLLECTION_NAME);
  }
  @Override
  public Chat getChatUserDAOName(String username) {
	  Query query = new Query(Criteria.where("username").is(username));
	  return mongoTemplate.findOne(query, Chat.class, COLLECTION_NAME);
  }
  @Override
  public Chat getChatRoomDAOName(String roomname) {
	  Query query = new Query(Criteria.where("roomname").is(roomname));
	  return mongoTemplate.findOne(query, Chat.class, COLLECTION_NAME);
  }
  @Override
  public Chat getChatRoomDAOPK(String roomPK) {
	  Query query = new Query(Criteria.where("roomPK").is(roomPK));
	  return mongoTemplate.findOne(query, Chat.class, COLLECTION_NAME);
  }

  @Override
  public List<Chat> getChats() {
    return (List<Chat>) mongoTemplate.findAll(Chat.class);
  }

  @Override
  public void deleteChat(Chat chat) {
  }

  @Override
  public Chat updateChat(Chat chat) {
    return chat;
  }

}
