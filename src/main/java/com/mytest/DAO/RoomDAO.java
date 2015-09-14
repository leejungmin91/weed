package com.mytest.DAO;

import com.mongodb.WriteResult;
import com.mytest.DTO.RoomDTO;

import java.util.List;

public interface RoomDAO {

  public RoomDTO insert(RoomDTO room);

  public List<RoomDTO> getRooms();

  public RoomDTO getRoomDAOReg_Date(String reg_data);
  
  public RoomDTO getRoomDAOName(String roomname);
  
  public RoomDTO getRoomDAOPK(String roomPK);
  
  public long getRoomNum();

  public void deleteRoom(RoomDTO room);

  public WriteResult updateRoom(String roomPK,String socketId);
}
