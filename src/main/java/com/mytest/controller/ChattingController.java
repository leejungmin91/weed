package com.mytest.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ChattingController {

	private static final Logger logger = LoggerFactory
			.getLogger(ChattingController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/chat.do", method = RequestMethod.POST)
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		logger.info("Welcome chat! The client locale is {}.");
		ModelAndView result = new ModelAndView();
		// logger.info("session_id_chat = " + chat_session);
		HashMap<String,String> map = new HashMap<String,String>();
		String session_fb_id = (String)request.getParameter("session_fb_id");
		String session_ko_name = (String)request.getParameter("session_ko_name");
		String session_gender = (String)request.getParameter("session_gender");
		String session_team = (String)request.getParameter("session_team");
		String session_team_PK = (String)request.getParameter("session_team_PK");
		map.put("session_fb_id", session_fb_id);
		map.put("session_ko_name", session_ko_name);
		map.put("session_gender", session_gender);
		map.put("session_team", session_team);
		map.put("session_team_PK", session_team_PK);
		session.setAttribute("map", map);
		result.setViewName("chat");
		return result;
	}

}
