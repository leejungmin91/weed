package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.UserDTO;

import java.util.List;

public interface UserDAO {

  public UserDTO insert(UserDTO user);

  public List<UserDTO> getUsers();

  public UserDTO getUserDAOName(String fb_id);
  
  public UserDTO getUserDAOFb_id(String fb_id);
  
  public void deleteUser(UserDTO user);

  public WriteResult updateUser(String fb_id, String roomPK);
}
