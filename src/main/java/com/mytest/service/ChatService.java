package com.mytest.service;

import com.mytest.DTO.Chat;

import java.util.List;

public interface ChatService {

  public void insertChat(Chat chat);

  public List<Chat> getChats();

  public Chat getChatContent(Chat chat);
  
  public Chat getChatReg_Data(Chat chat);
  
  public Chat getChatUserName(Chat username);
  
  public Chat getChatRoomName(Chat roomname);

  public boolean deleteChat(Chat chat);

  public Chat updateChat(Chat chat);
}
