package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.User;

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
	public User insert(User user) {
		mongoTemplate.insert(user, COLLECTION_NAME);
		return user;
	}

	@Override
	public User getUserDAOName(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		return mongoTemplate.findOne(query, User.class, COLLECTION_NAME);
	}

	@Override
	public User getUserDAOFb_id(String fb_id) {
		Query query = new Query(Criteria.where("fb_id").is(fb_id));

		return mongoTemplate.findOne(query, User.class, COLLECTION_NAME);
	}


	@Override
	public List<User> getUsers() {
		return (List<User>) mongoTemplate.findAll(User.class);
	}

	@Override
	public void deleteUser(User user) {
	}

	@Override
	public WriteResult updateUser(String fb_id, String roomPK) {
		
		Query query = new Query(Criteria.where("fb_id").is(fb_id));
		Update update = new Update();
		update.set("roomPK", roomPK);
		
		return mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

}
