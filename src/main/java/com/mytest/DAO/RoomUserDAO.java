package com.mytest.DAO;

import java.util.List;

import com.mongodb.WriteResult;
import com.mytest.DTO.RoomUserDTO;

public interface RoomUserDAO {

  public RoomUserDTO insert(RoomUserDTO room);
  
  public RoomUserDTO getRoomUserDAOFb_id(String fb_id);
  
  public RoomUserDTO getRoomUserDAOPK(String roomPK);
  
  public List<RoomUserDTO> getRoomUsers(String fb_id);
  
  public List<RoomUserDTO> getRooms();

  public void deleteRoomUser(RoomUserDTO room);

  public WriteResult updateRoomUser(String fb_id,String roomPK);
}
