package com.mytest.controller;

import java.util.HashMap;

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

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/chat", method = RequestMethod.POST)
public class ChattingController {

	private static final Logger logger = LoggerFactory
			.getLogger(ChattingController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private RoomDAOImpl roomDAOImpl;
	@RequestMapping(value = "/*", method = {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		
		logger.info("Welcome chat! The client locale is {}.");
		ModelAndView result = new ModelAndView();
		// logger.info("session_id_chat = " + chat_session);
		
		HashMap<String,String> map = new HashMap<String,String>();
		String session_fb_id = (String)request.getParameter("session_fb_id");
		String session_ko_name = (String)request.getParameter("session_ko_name");
		String session_team = (String)request.getParameter("session_team");
		
		logger.info("session_team =======================>: " + session_team);
		String session_team_PK = (String)request.getParameter("session_team_PK");
		if(roomDAOImpl.getRoomDAOPK(session_team_PK)!=null){
			session_team = roomDAOImpl.getRoomDAOPK(session_team_PK).getRoomName();
		}else{
		session_team = "";
		}
		map.put("session_fb_id", session_fb_id);
		map.put("session_ko_name", session_ko_name);
		map.put("session_team", session_team);
		map.put("session_team_PK", session_team_PK);
		
		
		request.getSession().setAttribute("map", map);
		//session.setAttribute("map", map);
		logger.info("user map : " + session_fb_id);
		result.setViewName("chat");
		return result;
		
	}

}
