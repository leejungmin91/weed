package com.mytest.DAO;

import java.util.List;

import com.mongodb.WriteResult;
import com.mytest.DTO.FileDTO;

public interface FileDAO {

  public FileDTO insert(FileDTO file);

  public List<FileDTO> getFiles();

  public FileDTO getFileDAOPK(long filePK);
  
  public long getFilesNum();
  
  public List<FileDTO> getFiles(String roomPK);

  public void deleteFile(FileDTO file);

  public FileDTO updateFile(FileDTO file);
  
  public FileDTO getFileDAORan_Name(String ran_filename);
  
}
