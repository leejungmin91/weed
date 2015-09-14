package com.mytest.DAO;

import com.mytest.DTO.ChatDTO;
import com.mytest.DTO.RoomUserDTO;

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
  public ChatDTO insert(ChatDTO chat) {
	logger.info("chat insert");
    mongoTemplate.insert(chat,COLLECTION_NAME);
    return chat;
  }
  @Override
  public ChatDTO getChatDAOReg_Date(String reg_date) {
	  Query query = new Query(Criteria.where("reg_date").is(reg_date));
	  return mongoTemplate.findOne(query, ChatDTO.class, COLLECTION_NAME);
  }

  @Override
  public ChatDTO getChatDAOContent(String content) {
	Query query = new Query(Criteria.where("content").is(content));
    return mongoTemplate.findOne(query, ChatDTO.class, COLLECTION_NAME);
  }
  @Override
  public ChatDTO getChatUserDAOName(String username) {
	  Query query = new Query(Criteria.where("username").is(username));
	  return mongoTemplate.findOne(query, ChatDTO.class, COLLECTION_NAME);
  }
  @Override
  public ChatDTO getChatRoomDAOName(String roomname) {
	  Query query = new Query(Criteria.where("roomname").is(roomname));
	  return mongoTemplate.findOne(query, ChatDTO.class, COLLECTION_NAME);
  }
  @Override
  public ChatDTO getChatRoomDAOPK(String roomPK) {
	  Query query = new Query(Criteria.where("roomPK").is(roomPK));
	  return mongoTemplate.findOne(query, ChatDTO.class, COLLECTION_NAME);
  }
  @Override
	public List<ChatDTO> getChats(String roomPK) {
		Query query = new Query(Criteria.where("roomPK").is(roomPK));
		return (List<ChatDTO>) mongoTemplate.find(query,ChatDTO.class , COLLECTION_NAME);
	}

  @Override
  public List<ChatDTO> getChats() {
    return (List<ChatDTO>) mongoTemplate.findAll(ChatDTO.class);
  }

  @Override
  public void deleteChat(ChatDTO chat) {
  }

  @Override
  public ChatDTO updateChat(ChatDTO chat) {
    return chat;
  }

}
