package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.UserDTO;

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
public class UserDAOImpl implements UserDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	private static String COLLECTION_NAME = "jabcho_user";

	private static final Logger logger = LoggerFactory
			.getLogger(UserDAOImpl.class);

	@Override
	public UserDTO insert(UserDTO user) {
		mongoTemplate.insert(user, COLLECTION_NAME);
		return user;
	}

	@Override
	public UserDTO getUserDAOName(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		return mongoTemplate.findOne(query, UserDTO.class, COLLECTION_NAME);
	}

	@Override
	public UserDTO getUserDAOFb_id(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, UserDTO.class, COLLECTION_NAME);
	}


	@Override
	public List<UserDTO> getUsers() {
		return (List<UserDTO>) mongoTemplate.findAll(UserDTO.class);
	}

	@Override
	public void deleteUser(UserDTO user) {
	}

	@Override
	public WriteResult updateUser(String fb_id, String roomPK) {
		
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		Update update = new Update();
		update.set("roomPK", roomPK);
		
		return mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

}
