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
			// ����key�� ���� ������ ���ϰ�� �α����������� �̵�
			// ���ͼ��� �� �պ��ߵ�

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
		// admin ����key ����� main ������ �̵�
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