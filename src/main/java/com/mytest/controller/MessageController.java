package com.mytest.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mytest.DAO.MessageDAOImpl;
import com.mytest.DAO.RoomDAOImpl;
import com.mytest.DAO.RoomUserDAOImpl;
import com.mytest.DTO.MessageDTO;
import com.mytest.DTO.RoomDTO;
import com.mytest.DTO.RoomUserDTO;

@Controller
public class MessageController {
	@Autowired
	private RoomUserDAOImpl roomuserDAOImpl;
	@Autowired
	private RoomDAOImpl roomDAOImpl;
	@Autowired
	private MessageDAOImpl messageDAOImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(MessageController.class);

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView result = new ModelAndView();
		result.setViewName("home");
		return result;
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpSession session) {
		logger.info("Welcome message! The client locale is {}.");

		String recv_fb_id = request.getParameter("recv_fb_id");
		String send_fb_id = request.getParameter("send_fb_id");
		String roomPK = request.getParameter("roomPK");
		String reg_date = request.getParameter("reg_date");
		
		MessageDTO message = new MessageDTO();
		message.setMessageRecvFb_id(recv_fb_id);
		message.setMessageSendFb_id(send_fb_id);
		message.setMessageRoomPK(roomPK);
		message.setMessageReg_Date(reg_date);		
		
		messageDAOImpl.insert(message);
		
		
		
		ModelAndView result = new ModelAndView();
		result.setViewName("team");
		return result;

	}
}