package kr.or.reservation.controller;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.http.HttpRequest;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
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
<<<<<<< HEAD

=======
	
	
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

<<<<<<< HEAD
=======

>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
	@GetMapping(path = "/callback")
	public String enrollSession(Model model, HttpSession session, @RequestParam String code,
			@RequestParam String state) {
		NaverLogin login = new NaverLogin();
		NaverUserDTO dto = null;
<<<<<<< HEAD
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
=======
		String url =  (String)session.getAttribute("originUrl");
		if (state.equals(session.getAttribute("state"))) {
			dto = login.convertToNaverDTO(code, state);
			if (dto != null) {
				if(loginService.progressLogin(dto)) {
					session.removeAttribute("originUrl");
					session.setAttribute("id", loginService.selectId(dto.getSnsId()));
					session.setAttribute("name", dto.getUsername());
					session.setAttribute("email", dto.getEmail());
					url = (url == null)?"/myPage":url;
				}
			}
		}
		
		return "redirect:"+url;
	}
	
	@GetMapping(path = "/my")
    public String selectAll(Model model,HttpSession session){
		if(session.getAttribute("id") !=null) {
    		return "redirect:/myPage";
    	}else {
    		NaverLogin login = new NaverLogin();
    		String url = login.getLoginURL(session);
    		return "redirect:"+url;
    	}
    }
	
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a

}