package com.mytest.DAO;

import com.mytest.DTO.MessageDTO;
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
public class MessageDAOImpl implements MessageDAO {

  @Autowired
  MongoTemplate mongoTemplate;
  private static final Logger logger = LoggerFactory
			.getLogger(MessageDAOImpl.class);
  private static String COLLECTION_NAME = "jabcho_message";

  @Override
  public MessageDTO insert(MessageDTO message) {
	logger.info("Message insert");
    mongoTemplate.insert(message,COLLECTION_NAME);
    return message;
  }

  @Override
  public MessageDTO getMessageDAO(String recv_fb_id) {
	  Query query = new Query(Criteria.where("recv_fb_id").is(recv_fb_id));
	  return mongoTemplate.findOne(query, MessageDTO.class, COLLECTION_NAME);
  }
  @Override
	public List<MessageDTO> getMessages(String roomPK) {
		Query query = new Query(Criteria.where("roomPK").is(roomPK));
		return (List<MessageDTO>) mongoTemplate.find(query,MessageDTO.class , COLLECTION_NAME);
	}

  @Override
  public List<MessageDTO> getMessages() {
    return (List<MessageDTO>) mongoTemplate.findAll(MessageDTO.class);
  }

  @Override
  public void deleteMessage(MessageDTO message) {
  }

  @Override
  public MessageDTO updateMessage(MessageDTO message) {
    return message;
  }

}
