/*package com.mytest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.mytest.DAO.FileDAOService;
//import com.mytest.DAO.MemberDAOService;
import com.mytest.DAO.UserDAOImpl;
//import com.mytest.DTO.FileDTO;
//import com.mytest.DTO.MemberDTO;
import com.mytest.DTO.User;

@Controller
public class MainController {
	@Autowired
	// private MemberDAOService memberDAOService;
	private UserDAOImpl userDAOImpl;
	@Autowired
	//private FileDAOService fileDAOService;
	private static final Logger logger = LoggerFactory
			.getLogger(MainController.class);

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView result = new ModelAndView();
		result.setViewName("home");
		return result;
	}

	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpSession session) {
		logger.info("Welcome main! The client locale is {}.");
		//ArrayList<FileDTO> list = fileDAOService.getFile();
		// MemberDTO member = new MemberDTO();
		User user = new User();

		logger.info("fb_id = " + (String) request.getParameter("fb_id"));
		
		 * logger.info("memberDAO fb_id = " +
		 * memberDAOService.getMemberFb_id((String) request
		 * .getParameter("fb_id")));
		 
		logger.info("user fb_id = "
				+ userDAOImpl.getUserFb_id((String) request
						.getParameter("fb_id")));

		if (((String) request.getParameter("fb_id")).equals((userDAOImpl
				.getUserFb_id((String) request.getParameter("fb_id"))))) {
			logger.info("Duplication name & fb_id!!");
			ModelAndView result = new ModelAndView();
			
			 * List<MemberDTO> memberList = memberDAOService
			 * .getMemberName((String) request.getParameter("name"));
			 
			// result.addObject("result", memberList);
			result.setViewName("main");
			session.setAttribute("name", request.getParameter("name"));
			// session.setAttribute("list", list);
			return result;
		} else {
			user.setUserName((String) request.getParameter("name"));
			user.setUserFb_id((String) request.getParameter("fb_id"));
			user.setGender((String) request.getParameter("gender"));
			userDAOImpl.insert(user);
			logger.info("insert complet");
			ModelAndView result = new ModelAndView();
			
			 * List<MemberDTO> memberList = memberDAOService
			 * .getMemberName((String) request.getParameter("name")); //
			 * result.addObject("result", memberList);
			 * 
			 result.setViewName("main");
			session.setAttribute("name", request.getParameter("name"));
			// session.setAttribute("list", list);

			return result;
		}

	}
}*/