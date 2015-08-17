package com.mytest.service;

import com.mytest.DTO.User;

import java.util.List;

public interface UserService {

  public void insertUser(User user);

  public List<User> getUsers();

  public User getUserName(User user);
  
  public User getUserFb_id(String fb_id);

  public boolean deleteUser(User user);

  public User updateUser(User user);
}
