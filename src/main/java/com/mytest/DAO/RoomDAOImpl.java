package com.mytest.DAO;

import com.mytest.DTO.Room;

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
  public Room insert(Room room) {
	logger.info("Room insert");
    mongoTemplate.insert(room,COLLECTION_NAME);
    return room;
  }
  @Override
  public Room getRoomDAOReg_Date(String reg_date) {
	  Query query = new Query(Criteria.where("reg_date").is(reg_date));
	  return mongoTemplate.findOne(query, Room.class, COLLECTION_NAME);
  }

  @Override
  public Room getRoomDAOName(String userName) {
	  Query query = new Query(Criteria.where("username").is(userName));
	  return mongoTemplate.findOne(query, Room.class, COLLECTION_NAME);
  }

  @Override
  public Room getRoomDAOPK(String roomPK) {
	  Query query = new Query(Criteria.where("roomPK").is(roomPK));
	  return mongoTemplate.findOne(query, Room.class, COLLECTION_NAME);
  }

  @Override
  public List<Room> getRooms() {
    return (List<Room>) mongoTemplate.findAll(Room.class);
  }

  @Override
  public void deleteRoom(Room Room) {
  }

  @Override
  public Room updateRoom(Room Room) {
    return Room;
  }

}
