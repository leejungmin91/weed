package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.User;

import java.util.List;

public interface UserDAO {

  public User insert(User user);

  public List<User> getUsers();

  public User getUserDAOName(String fb_id);
  
  public User getUserDAOFb_id(String fb_id);
  
  public void deleteUser(User user);

  public WriteResult updateUser(String fb_id, String roomPK);
}
