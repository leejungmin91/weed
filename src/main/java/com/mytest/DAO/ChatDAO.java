package com.mytest.DAO;

import com.mytest.DTO.Chat;

import java.util.List;

public interface ChatDAO {

  public Chat insert(Chat chat);

  public List<Chat> getChats();

  public Chat getChatDAOContent(String content);
  
  public Chat getChatDAOReg_Date(String reg_date);
  
  public Chat getChatUserDAOName(String username);
  
  public Chat getChatRoomDAOName(String roomname);
  
  public Chat getChatRoomDAOPK(String roomPK);

  public void deleteChat(Chat chat);

  public Chat updateChat(Chat chat);
}
