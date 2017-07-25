package kr.or.connect.controller;

import java.io.*;
import java.net.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import kr.or.connect.service.*;
import kr.or.connect.util.*;

@Controller
@RequestMapping("/my-reservation")
public class MyReservationController {

	private static final String CLIENT_ID = "UT0zsTGjviSL7f6l7c1Q";
	private static final String NAVER_OAUTH_AUTHORIZE_URL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="
			+ CLIENT_ID + "&redirect_uri=";
	private static final String TARGET_JSP = "myreservation";

	private String redirectUri;
	private UserService userService;
	private GetStateUtil getStateUtil;

	@Autowired
	public MyReservationController(UserService userService, GetStateUtil getStateUtil) {
		super();
		this.userService = userService;
		this.getStateUtil = getStateUtil;
	}

	@GetMapping
	public String check(HttpSession session, Model model, HttpServletRequest request) {
		if (session.getAttribute("email") != null) {
			if(userService.selectByEmail((String)session.getAttribute("email")) != null) {
				model.addAttribute("user", userService.selectByEmail((String)session.getAttribute("email")));
				return TARGET_JSP;
			} 
		} 
		String state = getStateUtil.getState();
		session.setAttribute("state", state);
		String targetURI = request.getRequestURI();
		try {
			this.redirectUri = URLEncoder.encode("http://localhost:8080/login" + targetURI, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:" + NAVER_OAUTH_AUTHORIZE_URL + this.redirectUri + "&state=" + state;
	}
	
}
