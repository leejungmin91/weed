package com.mytest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.mytest.DAO.FileDAOImpl;
import com.mytest.DTO.FileDTO;

public class FileDownloadView extends AbstractView {
	private static final Logger logger = LoggerFactory
			.getLogger(FileDownloadView.class);

	public FileDownloadView() {
		setContentType("applicaiton/download;charset=utf-8");
	}
	/**
	* 브라우저 종류
	* @param request
	* @return
	*/
	@Autowired
	private FileDAOImpl fileDAOImpl;
	

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File file = (File) model.get("downloadFile");
		
		//String browser = request.getHeader("User-Agent");
		// 파일 인코딩
		/*String fileoriginalname = file.getName();
		int Idx = file.getName().lastIndexOf(".");
		String filenameroomPKcut = file.getName().substring(0, Idx);
		String filenamecutext = file.getName().substring(Idx + 1);
		int Idx2 = filenameroomPKcut.lastIndexOf("_");
		String filenamecutoriginal = filenameroomPKcut.substring(0, Idx2);
		String filename = filenamecutoriginal + "." + filenamecutext;*/
		String ran_filename = file.getName();
		//String original_filename = fileDAOImpl.getFileDAORan_Name(ran_filename).getFileName();
		
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		String originalfilename = null;
		originalfilename = fileDAOImpl.getFileDAORan_Name(ran_filename).getFileName();
		// String fileName = java.net.URLEncoder.encode(file.getName(),"UTF-8");
		// String fileName = file.getName();
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ originalfilename + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			
			fis = new FileInputStream(file);
			
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e2) {
				}
			}
		}
		out.flush();
	}
}
