package com.mytest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mytest.DAO.FileDAOService;
import com.mytest.DTO.FileDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	
	private FileDAOService FileDAOService;

	private WebApplicationContext context = null;

	@RequestMapping(value = "/filedown.do", method = RequestMethod.GET)
	public ModelAndView fileDown(@RequestParam("fileName") String fileName) {
		String fullPath = "C:/JabchoServerFile/" + fileName;
		File downloadFile = new File(fullPath);
		return new ModelAndView("download", "downloadFile", downloadFile);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
/*
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView fileList() {
		logger.info("Welcome list");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");

		ArrayList<FileDTO> list = FileDAOService.getFile();
		mv.addObject("list", list);
		return mv;
	}
*/
	@RequestMapping(value = "/fileup.do", method = RequestMethod.POST)
	public String fileSubmit(FileDTO dto) {
		MultipartFile uploadfile = dto.getUploadfile();
		if (uploadfile != null) {
			String fileName = uploadfile.getOriginalFilename();
			long fileSize = uploadfile.getSize();
			dto.setFileName(fileName);
			dto.setFileSize(fileSize);
			logger.info("filename = " + fileName);
			logger.info("fileSize = " + fileSize);
			try {
				// 1. FileOutputStream 사용
				// byte[] fileData = file.getBytes();
				// FileOutputStream output = new FileOutputStream("C:/images/" +
				// fileName);
				// output.write(fileData);

				// 2. File 사용
				File file = new File("C:/JabchoServerFile/" + fileName);
				uploadfile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			} // try - catch
		} // if
		logger.info("dto insert before");
		FileDAOService.insertFile(dto);

		return "redirect:main.do";
	}
}
