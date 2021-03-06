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

import com.mytest.DAO.RoomDAOImpl;
import com.mytest.DAO.RoomUserDAOImpl;
import com.mytest.DTO.RoomDTO;
import com.mytest.DTO.RoomUserDTO;

@Controller
public class TeamCreateController {
	@Autowired
	private RoomUserDAOImpl roomuserDAOImpl;
	@Autowired
	private RoomDAOImpl roomDAOImpl;

	private GregorianCalendar calendar = new GregorianCalendar();

	private static final Logger logger = LoggerFactory
			.getLogger(TeamCreateController.class);

	@RequestMapping(value = "/teamcreater", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView result = new ModelAndView();
		result.setViewName("home");
		return result;
	}

	@RequestMapping(value = "/teamcreater", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpSession session) {
		logger.info("Welcome teamcreater! The client locale is {}.");

		int year = calendar.get(Calendar.YEAR); // 연도를 리턴
		int month = calendar.get(Calendar.MONTH);// 월을 리턴
		int date = calendar.get(Calendar.DATE);// 일을 리턴
		int amPm = calendar.get(Calendar.AM_PM);// 오전/오후구분을 리턴
		int hour = calendar.get(Calendar.HOUR);// 시를 리턴
		int min = calendar.get(Calendar.MINUTE);// 분을 리턴
		int sec = calendar.get(Calendar.SECOND);// 초를 리턴
		String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
		final String Reg_Date = year + "년 " + month + "월 " + date + "일 "
				+ sAmPm + " " + hour + "시 " + min + "분 " + sec + "초 ";

		RoomUserDTO roomuser = new RoomUserDTO();
		RoomDTO room = new RoomDTO();
		logger.info("roomPK"+(String) request
				.getParameter("roomPK"));

		if ((roomuserDAOImpl.getRoomUserDAOPK((String) request
				.getParameter("roomPK")) == null)) {
			logger.info("---------------------------->"+request
					.getParameter("roomPK"));
			roomuser.setRoomUserFb_id((String) request.getParameter("fb_id"));
			roomuser.setRoomUserPK((String) request.getParameter("roomPK"));
			roomuser.setRoomUserName((String) request.getParameter("roomname"));
			roomuserDAOImpl.insert(roomuser);
			room.setRoomName((String) request.getParameter("roomname"));
			room.setRoomPK((String) request.getParameter("roomPK"));
			room.setRoomReg_Date(Reg_Date);
			roomDAOImpl.insert(room);
			
			logger.info("null insert complet");
			ModelAndView result = new ModelAndView();
			result.setViewName("team");
			session.setAttribute("name", request.getParameter("name"));
			return result;
		} else {
			
			if (((String) request.getParameter("roomPK"))
					.equals((roomuserDAOImpl.getRoomUserDAOPK((String) request
							.getParameter("roomPK")).getRoomUserPK()))) {
				logger.info("existed namePK!!");
				ModelAndView result = new ModelAndView();
				result.setViewName("team");
				// session.setAttribute("name", request.getParameter("name"));
				return result;
			} else {
				logger.info("fail roomCreater!!");
				ModelAndView result = new ModelAndView();

				result.setViewName("team");
				return result;
			}
		}

	}
}