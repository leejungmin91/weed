package com.mytest.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Auth {
	
	/*@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		HashMap<String , String> map = new HashMap<String, String>();
		map.put("fb_id",(String) request.getParameter("fb_id"));
		map.put("ko_name",(String) request.getParameter("name"));
		map.put("gender",(String) request.getParameter("gender"));
		request.getSession().setAttribute("session_map", map);
		return "team";
	}*/
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:"+request.getParameter("logoutURL");
	}
}
