package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.reservation.api.NaverLogin;


@Controller
public class MainController {
	Logger log = Logger.getLogger(this.getClass());
	
    @GetMapping(path = "/")
    public String viewMain(Model model){
    	
    	return "mainpage";
    }
 
}