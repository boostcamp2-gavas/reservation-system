package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.reservation.api.NaverLogin;
import kr.or.reservation.dto.NaverUserDTO;
import kr.or.reservation.service.LoginService;

@Controller
public class LoginController {
	// 로그 선언
	Logger log = Logger.getLogger(this.getClass());
	LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping(path = "/callback")
	public String enrollSession(Model model, HttpSession session, @RequestParam String code,
			@RequestParam String state) {
		NaverLogin login = new NaverLogin();
		NaverUserDTO dto = null;
		if (state.equals(session.getAttribute("state"))) {
			dto = login.convertToNaverDTO(code, state);
			if (dto != null) {
				loginService.progressLogin(dto);
				session.setAttribute("id", dto.getSnsId());
				session.setAttribute("name", dto.getUsername());
				session.setAttribute("email", dto.getEmail());
			}
		}
		return "redirect:/";
	}

}