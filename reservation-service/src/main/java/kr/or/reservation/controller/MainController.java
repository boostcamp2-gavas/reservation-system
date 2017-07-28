package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	Logger log = Logger.getLogger(this.getClass());
	

	
    @GetMapping(path = "/")
    public String viewMain(Model model,HttpSession session){
    	// session 이 존재하면, mypageURI를 줌 
    	// 이 부분 여러 페이지에서 사용될 수 있으니, 함수로 따로 뺄까 ? 
    	/*if(session.getAttribute("id") !=null) {
    		model.addAttribute("loginURL", "/my");
    	}else {
    		NaverLogin login = new NaverLogin();
    		NaverLogin.setCLIENT_ID(CLIENT_ID);
    		NaverLogin.setREDIRECT_URL(REDIRECT_URL);
    		NaverLogin.setSECRET_ID(SECRET_ID);
    		String url = login.getLoginURL(session);
        	model.addAttribute("loginURL", url);
    	}*/
    	session.setAttribute("id", 15);
		session.setAttribute("name", "Jangchulwoon");
		session.setAttribute("email", "lusiue@naver.com");
		model.addAttribute("loginURL", "/my");
    	return "mainpage";
    }
 
}