package com.mytest.controller;

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
//import com.mytest.DAO.FileDAOService;
//import com.mytest.DAO.MemberDAOService;
import com.mytest.DAO.UserDAOImpl;
//import com.mytest.DTO.FileDTO;
//import com.mytest.DTO.MemberDTO;
import com.mytest.DTO.User;

@Controller
public class TeamController {
	@Autowired
	private UserDAOImpl userDAOImpl;

	@Autowired
	private RoomUserDAOImpl roomuserDAOImpl;
	@Autowired
	// private FileDAOService fileDAOService;
	private static final Logger logger = LoggerFactory
			.getLogger(TeamController.class);

	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public ModelAndView get(HttpServletRequest request) {
		ModelAndView result = new ModelAndView();
		result.setViewName("home");
		return result;
	}

	@RequestMapping(value = "/team", method = RequestMethod.POST)
	public ModelAndView insert(HttpServletRequest request, HttpSession session) {
		logger.info("Welcome team! The client locale is {}.");
		// ArrayList<FileDTO> list = fileDAOService.getFile();
		// MemberDTO member = new MemberDTO();
		User user = new User();

		logger.info("userDAO class = "
				+ userDAOImpl.getUserDAOFb_id((String) request
						.getParameter("fb_id")));
		logger.info("userDAOImpl class = " + userDAOImpl);

		/*
		 * if (((String) request.getParameter("fb_id")).equals((userDAOImpl
		 * .getUserDAOFb_id((String)
		 * request.getParameter("fb_id")).getUserFb_id()))) {
		 */
		if ((userDAOImpl
				.getUserDAOFb_id((String) request.getParameter("fb_id")) != null)) {
			if (((String) request.getParameter("fb_id")).equals((userDAOImpl
					.getUserDAOFb_id((String) request.getParameter("fb_id"))
					.getUserFb_id()))) {
				logger.info("existed name & fb_id!!");
				ModelAndView result = new ModelAndView();
				/*
				 * List<MemberDTO> memberList = memberDAOService
				 * .getMemberName((String) request.getParameter("name"));
				 */
				// result.addObject("result", memberList);
				result.setViewName("team");
				result.addObject("userDAOImpl", userDAOImpl);
				result.addObject("roomuserDAOImpl", roomuserDAOImpl);
				session.setAttribute("name", request.getParameter("name"));
				// session.setAttribute("list", list);
				return result;
			} else {
				logger.info("fail login!!");
				ModelAndView result = new ModelAndView();

				result.setViewName("home");
				return result;
			}
		} else {
			user.setUserName((String) request.getParameter("name"));
			user.setUserFb_id((String) request.getParameter("fb_id"));
			user.setGender((String) request.getParameter("gender"));
			userDAOImpl.insert(user);
			logger.info("insert complet");
			ModelAndView result = new ModelAndView();
			/*
			 * List<MemberDTO> memberList = memberDAOService
			 * .getMemberName((String) request.getParameter("name")); //
			 * result.addObject("result", memberList);
			 */result.setViewName("team");
			session.setAttribute("name", request.getParameter("name"));
			// session.setAttribute("list", list);
			result.addObject("userDAOImpl", userDAOImpl);
			result.addObject("roomuserDAOImpl", roomuserDAOImpl);

			return result;
		}

	}
}