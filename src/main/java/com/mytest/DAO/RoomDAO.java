package com.mytest.DAO;

import com.mytest.DTO.Room;

import java.util.List;

public interface RoomDAO {

  public Room insert(Room room);

  public List<Room> getRooms();

  public Room getRoomDAOReg_Date(String reg_data);
  
  public Room getRoomDAOName(String roomname);
  
  public Room getRoomDAOPK(String roomPK);

  public void deleteRoom(Room room);

  public Room updateRoom(Room room);
}
