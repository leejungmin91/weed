package com.mytest.DAO;

import com.mytest.DTO.MessageDTO;

import java.util.List;

public interface MessageDAO {

  public MessageDTO insert(MessageDTO message);

  public List<MessageDTO> getMessages();

  
  public MessageDTO getMessageDAO(String recv_fb_id);
  
  public List<MessageDTO> getMessages(String roomPK);

  public void deleteMessage(MessageDTO message);

  public MessageDTO updateMessage(MessageDTO message);
}
