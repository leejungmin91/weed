package com.mytest.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.mytest.DTO.RoomDTO;
import com.mytest.DTO.RoomUserDTO;
import com.mytest.DTO.UserDTO;

@Repository
public class RoomUserDAOImpl implements RoomUserDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	private static String COLLECTION_NAME = "jabcho_roomuser";

	private static final Logger logger = LoggerFactory
			.getLogger(RoomUserDAOImpl.class);

	@Override
	public RoomUserDTO insert(RoomUserDTO roomuser) {
		mongoTemplate.insert(roomuser, COLLECTION_NAME);
		return roomuser;
	}

	@Override
	public RoomUserDTO getRoomUserDAOFb_id(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, RoomUserDTO.class, COLLECTION_NAME);
	}

	@Override
	public RoomUserDTO getRoomUserDAOPK(String roomPK) {
		Query query = new Query(Criteria.where("roomPK").is(roomPK));

		return mongoTemplate.findOne(query, RoomUserDTO.class, COLLECTION_NAME);
	}

	@Override
	public List<RoomUserDTO> getRoomUsers(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		return (List<RoomUserDTO>) mongoTemplate.find(query,RoomUserDTO.class , COLLECTION_NAME);
	}
	@Override
	public List<RoomUserDTO> getRooms() {
		return (List<RoomUserDTO>) mongoTemplate.findAll(RoomUserDTO.class , COLLECTION_NAME);
	}

	@Override
	public void deleteRoomUser(RoomUserDTO user) {
	}

	@Override
	public WriteResult updateRoomUser(String fb_id, String roomPK) {

		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		Update update = new Update();
		update.set("roomPK", roomPK);

		return mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

}
