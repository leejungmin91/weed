package com.mytest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		try {
			// 세션key를 가진 정보가 널일경우 로그인페이지로 이동
			// 인터셉터 더 손봐야됨

			if (session.getAttribute("email") == null) {
				log.debug("====================================== "
						+ session.getAttribute("email")
						+ " ======================================\n");
				// response.sendRedirect("/test/home.do");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// admin 세션key 존재시 main 페이지 이동
		log.debug("====================================== "
				+ session.getAttribute("email")
				+ " ======================================\n");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			// log.debug("======================================           END          ======================================\n");
		}
	}
}