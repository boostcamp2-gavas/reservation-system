package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

<<<<<<< HEAD
=======
import org.apache.log4j.Logger;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.reservation.api.NaverLogin;

import kr.or.reservation.api.NaverLogin;


@Controller
public class MainController {
	Logger log = Logger.getLogger(this.getClass());
	
    @GetMapping(path = "/")
<<<<<<< HEAD
    public String viewMain(Model model,HttpSession session){
    	// session 이 존재하면, mypageURI를 줌 
    	// 이 부분 여러 페이지에서 사용될 수 있으니, 함수로 따로 뺄까 ? 
    	if(session.getAttribute("id") !=null) {
    		model.addAttribute("loginURL", "/my");
    	}else {
    		NaverLogin login = new NaverLogin();
    		String url = login.getLoginURL(session);
        	model.addAttribute("loginURL", url);
    	}
=======
    public String viewMain(Model model){
    	
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
    	return "mainpage";
    }
 
}