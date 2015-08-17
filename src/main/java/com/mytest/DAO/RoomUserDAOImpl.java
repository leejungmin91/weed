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
import com.mytest.DTO.Room;
import com.mytest.DTO.RoomUser;
import com.mytest.DTO.User;

@Repository
public class RoomUserDAOImpl implements RoomUserDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	private static String COLLECTION_NAME = "jabcho_roomuser";

	private static final Logger logger = LoggerFactory
			.getLogger(RoomUserDAOImpl.class);

	@Override
	public RoomUser insert(RoomUser roomuser) {
		mongoTemplate.insert(roomuser, COLLECTION_NAME);
		return roomuser;
	}

	@Override
	public RoomUser getRoomUserDAOReg_Date(String reg_date) {
		Query query = new Query(Criteria.where("reg_date").is(reg_date));

		return mongoTemplate.findOne(query, RoomUser.class, COLLECTION_NAME);
	}

	@Override
	public RoomUser getRoomUserDAOFb_id(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, RoomUser.class, COLLECTION_NAME);
	}

	@Override
	public RoomUser getRoomUserDAOPK(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, RoomUser.class, COLLECTION_NAME);
	}

	@Override
	public RoomUser getRoomUserDAOName(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, RoomUser.class, COLLECTION_NAME);
	}

	@Override
	public List<RoomUser> getRoomUsers(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		return (List<RoomUser>) mongoTemplate.find(query,RoomUser.class , COLLECTION_NAME);
	}

	@Override
	public void deleteRoomUser(RoomUser user) {
	}

	@Override
	public WriteResult updateRoomUser(String fb_id, String roomPK) {

		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		Update update = new Update();
		update.set("roomPK", roomPK);

		return mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

}
