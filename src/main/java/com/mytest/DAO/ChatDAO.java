package com.mytest.DAO;

import com.mytest.DTO.ChatDTO;

import java.util.List;

public interface ChatDAO {

  public ChatDTO insert(ChatDTO chat);

  public List<ChatDTO> getChats();

  public ChatDTO getChatDAOContent(String content);
  
  public ChatDTO getChatDAOReg_Date(String reg_date);
  
  public ChatDTO getChatUserDAOName(String username);
  
  public ChatDTO getChatRoomDAOName(String roomname);
  
  public ChatDTO getChatRoomDAOPK(String roomPK);
  
  public List<ChatDTO> getChats(String roomPK);

  public void deleteChat(ChatDTO chat);

  public ChatDTO updateChat(ChatDTO chat);
}
