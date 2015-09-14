package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.RoomDTO;
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
public class RoomDAOImpl implements RoomDAO {

  @Autowired
  MongoTemplate mongoTemplate;
  private static final Logger logger = LoggerFactory
			.getLogger(RoomDAOImpl.class);
  private static String COLLECTION_NAME = "jabcho_room";

  @Override
  public RoomDTO insert(RoomDTO room) {
	logger.info("Room insert");
    mongoTemplate.insert(room,COLLECTION_NAME);
    return room;
  }
  @Override
  public RoomDTO getRoomDAOReg_Date(String reg_date) {
	  Query query = new Query(Criteria.where("reg_date").is(reg_date));
	  return mongoTemplate.findOne(query, RoomDTO.class, COLLECTION_NAME);
  }

  @Override
  public RoomDTO getRoomDAOName(String userName) {
	  Query query = new Query(Criteria.where("username").is(userName));
	  return mongoTemplate.findOne(query, RoomDTO.class, COLLECTION_NAME);
  }

  @Override
  public RoomDTO getRoomDAOPK(String roomPK) {
	  Query query = new Query(Criteria.where("roomPK").is(roomPK));
	  return mongoTemplate.findOne(query, RoomDTO.class, COLLECTION_NAME);
  }
  @Override
  public long getRoomNum() {
	  Query query = new Query(Criteria.where("roomPK"));
	  return mongoTemplate.count(query,COLLECTION_NAME);
  }

  @Override
  public List<RoomDTO> getRooms() {
	return (List<RoomDTO>) mongoTemplate.findAll(RoomDTO.class , COLLECTION_NAME);
  }

  @Override
  public void deleteRoom(RoomDTO Room) {
  }

  @Override
	public WriteResult updateRoom(String roomPK,String socketId) {

		Query query = new Query(Criteria.where("roomPK").is(roomPK));
		Update update = new Update();
		update.set("socketId", socketId);

		return mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

}
