package kr.or.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
    @GetMapping(path = "/api/login")
    public ModelAndView viewMain(Model model){
    	ModelAndView mav = new ModelAndView("login/login");
    	return mav;
    }
    @GetMapping(path = "/login")
    public ModelAndView callback(Model model){
    	ModelAndView mav = new ModelAndView("login/loginCallback");
    	return mav;
    }
}