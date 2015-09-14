package com.mytest.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.vertx.java.core.http.HttpServerRequest;

import com.mytest.DAO.FileDAO;
import com.mytest.DAO.FileDAOImpl;
import com.mytest.DTO.FileDTO;
import com.mytest.service.VertxService;

@Controller
public class FileController implements ApplicationContextAware{
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileDAOImpl fileDAOImpl;
	private long index;
	private int fileindex = 1;
	private WebApplicationContext context = null;
	private Date date = new Date();
	private VertxService vertx;

	@RequestMapping(value = "/filedown", method = RequestMethod.GET)
	public ModelAndView fileDown(@RequestParam("fileName") String ran_filename) {
		String result = null;
		try {
			result = java.net.URLDecoder.decode(ran_filename, "UTF-8");
			//result = java.net.URLDecoder.decode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String originalfilename = null;
		long fileindex = Long.parseLong(result);
		ran_filename = fileDAOImpl.getFileDAOPK(fileindex).getRanFileName();
		String fullPath = "C:/JabchoServerFile/" + ran_filename;
		File downloadFile = new File(fullPath);
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	
	@RequestMapping(value = "/fileupload.do") //ajax에서 호출하는 부분
	@ResponseBody
	public HashMap<String,String> upload(MultipartHttpServletRequest multipartRequest) { //Multipart로 받는다.
	    Iterator<String> itr =  multipartRequest.getFileNames();
	    String roomPK = (String) multipartRequest.getParameter("roomPK");
	    String ran_filename = null;
	    String filenamecut1 = null;
	    logger.info("fileController ===========>"+roomPK);
	    while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
	    	MultipartFile mpf = multipartRequest.getFile(itr.next());
		    String originFileName = mpf.getOriginalFilename();
		    String reg_date = date.toLocaleString();
		   /* int Idx = originFileName .lastIndexOf(".");

			 String filenamecut0 = originFileName.substring(0, Idx );
			 filenamecut0 = filenamecut0+"_"+roomPK;
			 String filenamecut1 = originFileName.substring(Idx+1);
			 originFileName = filenamecut0+"."+filenamecut1;*/
		    
			 /*List<FileDTO> filelist = new ArrayList<FileDTO>();
				filelist = fileDAOImpl.getFiles(roomPK);
				String DBfilename;
				String DBran_filename;
				for (int i = 0; i < filelist.size(); i++) {
					DBfilename = filelist.get(i).getFileName();
					DBran_filename = filelist.get(i).getRanFileName();
					originFileName = DBran_filename;
					fileindex++;
					if(DBfilename.equals(originFileName)){
						
						originFileName = filenamecut0+"("+fileindex+")."+filenamecut1; 
						fileindex++;
					}
					logger.info("filecontroller file ===>"+originFileName);
				}*/
		    String filename = originFileName;
		    int Idx = originFileName .lastIndexOf(".");

			 String filenamecut0 = originFileName.substring(0, Idx );
			 filenamecut1 = originFileName.substring(Idx+1);
			 //filename = filenamecut0;
		    long filesize = mpf.getSize();
		    FileDTO fileDTO = new FileDTO();
			index = fileDAOImpl.getFilesNum();
			ran_filename = vertx.getRandomString();
			logger.info("fileController ========>"+ran_filename);
			
			multipartRequest.setAttribute("ran_filename", ran_filename);
			
			if (fileDAOImpl.getFiles() != null) {
				List<FileDTO> filelist = new ArrayList<FileDTO>();
				filelist = fileDAOImpl.getFiles(roomPK);
				String DBfilename;
				int fileindex = 1;
				fileDTO.setFileName(filename);
				fileDTO.setFileSize(filesize);
				fileDTO.setRanFileRanName(ran_filename+"."+filenamecut1);
				fileDTO.setFileRoomPK(roomPK);
				fileDTO.setFilePK(index + 1);
				fileDTO.setFileReg_date(reg_date);
				fileDAOImpl.insert(fileDTO);
				logger.info("file insert not index zero success");
			} else {
				// filename = filenamecut0+"."+filenamecut1;
				fileDTO.setFileName(filename);
				fileDTO.setFileSize(filesize);
				fileDTO.setRanFileRanName(ran_filename+"."+filenamecut1);
				fileDTO.setFileRoomPK(roomPK);
				fileDTO.setFilePK(1);
				fileDTO.setFileReg_date(reg_date);
				fileDAOImpl.insert(fileDTO);
				logger.info("file insert null success");
			}
		    
		    try {
			    
                File file = new File("C:/JabchoServerFile/" + ran_filename+"."+filenamecut1);
                mpf.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            } // try - catch
       }
	    HashMap<String, String> hm = new HashMap<String, String>();
	    hm.put("ran_filename", ran_filename+"."+filenamecut1);
	    
	    return hm;
	    
	    		}
}
