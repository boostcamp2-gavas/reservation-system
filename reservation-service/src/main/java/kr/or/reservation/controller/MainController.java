package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.api.NaverLogin;


@Controller
public class MainController {
	
    @GetMapping(path = "/")
    public String viewMain(Model model,HttpSession session){
    	// session 이 존재하면, mypageURI를 줌 
    	if(session.getAttribute("id") !=null) {
    		model.addAttribute("loginURL", "/my");
    	}else {
    		String url = NaverLogin.getLoginURL(session);
        	model.addAttribute("loginURL", url);
    	}
    	return "mainpage";
    }
 
}