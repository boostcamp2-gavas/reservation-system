package kr.or.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.UserReservationService;


@Controller
public class UserReservationController {
	
	UserReservationService userReservationService;
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public void setUserReservationService(UserReservationService userReservationService) {
		this.userReservationService = userReservationService;
	}

	@GetMapping(path = "/my")
    public String selectAll(Model model,HttpSession session){
		int sessionId= (Integer)session.getAttribute("id");
		model.addAttribute("count",userReservationService.selectTypeCount(sessionId));
    	return "myreservation";
    }
	
	
  

}