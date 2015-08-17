package com.mytest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytest.DAO.UserDAOImpl;
import com.mytest.DTO.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserDAOImpl userDAOImpl;

  @Override
  public void insertUser(User user) {
    logger.info("userServiceImple.insertUser: {}" + user);
    userDAOImpl.insert(user);
  }

  @Override
  public List<User> getUsers() {
    return userDAOImpl.getUsers();
  }

  @Override
  public User getUserName(User user) {
    logger.info("userServiceImple.getUserName: {}", user);
    return (User) userDAOImpl.getUserName(user);
  }
  @Override
  public User getUserFb_id(String fb_id) {
    logger.info("userServiceImple.getUserFb_id: {}", fb_id);
    return (User) userDAOImpl.getUserFb_id(fb_id);
  }

  @Override
  public boolean deleteUser(User user) {
    logger.info("userServiceImple.delUser: {}" + user);
    try {
    	userDAOImpl.deleteUser(user);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public User updateUser(User user) {

    return userDAOImpl.updateUser(user);

  }
}
