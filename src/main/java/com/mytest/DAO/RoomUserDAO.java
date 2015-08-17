package com.mytest.DAO;

import java.util.List;

import com.mongodb.WriteResult;
import com.mytest.DTO.RoomUser;

public interface RoomUserDAO {

  public RoomUser insert(RoomUser room);

  public RoomUser getRoomUserDAOReg_Date(String reg_data);
  
  public RoomUser getRoomUserDAOFb_id(String fb_id);
  
  public RoomUser getRoomUserDAOPK(String fb_id);
  
  public RoomUser getRoomUserDAOName(String fb_id);
  
  public List<RoomUser> getRoomUsers(String fb_id);

  public void deleteRoomUser(RoomUser room);

  public WriteResult updateRoomUser(String fb_id,String roomPK);
}
