package com.mytest.service;

import com.mytest.DAO.ChatDAO;
import com.mytest.DTO.Chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

  private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

  @Autowired
  private ChatDAO chatDAO;

  @Override
  public void insertChat(Chat chat) {
    logger.info("chatServiceImple.insertchat: {}" + chat);
    chatDAO.insert(chat);
  }

  @Override
  public List<Chat> getChats() {
    return chatDAO.getChats();
  }
  @Override
  public Chat getChatReg_Data(Chat chat) {
    logger.info("chatServiceImple.getChatReg_Data: {}", chat);
    return (Chat) chatDAO.getChatReg_Data(chat);
  }

  @Override
  public Chat getChatContent(Chat chat) {
    logger.info("chatServiceImple.getChatContent: {}", chat);
    return (Chat) chatDAO.getChatContent(chat);
  }
  @Override
  public Chat getChatUserName(Chat chat) {
    logger.info("chatServiceImple.getchatUserName: {}", chat);
    return (Chat) chatDAO.getChatUserName(chat.getChatUserName());
  }
  @Override
  public Chat getChatRoomName(Chat chat) {
    logger.info("chatServiceImple.getchatRoomName: {}", chat);
    return (Chat) chatDAO.getChatRoomName(chat.getChatRoomName());
  }

  @Override
  public boolean deleteChat(Chat chat) {
    logger.info("chatServiceImple.delchat: {}" + chat);
    try {
      chatDAO.deleteChat(chat);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public Chat updateChat(Chat chat) {

    return chatDAO.updateChat(chat);

  }
}
