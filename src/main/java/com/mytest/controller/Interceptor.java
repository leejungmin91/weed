package com.mytest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory
			.getLogger(Interceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		try{
			if(request.getSession().getAttribute("session_map")==null)
			{
				logger.info("Interceptor=============> session null");
				response.sendRedirect("/test/home.do");
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
}