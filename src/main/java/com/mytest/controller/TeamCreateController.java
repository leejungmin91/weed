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

import com.mytest.DAO.RoomUserDAOImpl;
import com.mytest.DTO.RoomUser;

@Controller
public class TeamCreateController {
	@Autowired
	private RoomUserDAOImpl roomuserDAOImpl;

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

		int year = calendar.get(Calendar.YEAR); // ������ ����
		int month = calendar.get(Calendar.MONTH);// ���� ����
		int date = calendar.get(Calendar.DATE);// ���� ����
		int amPm = calendar.get(Calendar.AM_PM);// ����/���ı����� ����
		int hour = calendar.get(Calendar.HOUR);// �ø� ����
		int min = calendar.get(Calendar.MINUTE);// ���� ����
		int sec = calendar.get(Calendar.SECOND);// �ʸ� ����
		String sAmPm = amPm == Calendar.AM ? "����" : "����";
		final String Reg_Date = year + "�� " + month + "�� " + date + "�� "
				+ sAmPm + " " + hour + "�� " + min + "�� " + sec + "�� ";

		RoomUser roomuser = new RoomUser();

		if ((roomuserDAOImpl.getRoomUserDAOPK((String) request
				.getParameter("roomPK")) != null)) {
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
		} else {
			roomuser.setRoomUserFb_id((String) request.getParameter("fb_id"));
			roomuser.setUserReg_date(Reg_Date);
			roomuser.setRoomUserPK((String) request.getParameter("roomPK"));
			roomuser.setRoomUserName((String) request.getParameter("roomname"));
			roomuserDAOImpl.insert(roomuser);
			
			logger.info("insert complet");
			ModelAndView result = new ModelAndView();
			result.setViewName("team");
			session.setAttribute("name", request.getParameter("name"));
			return result;
		}

	}
}