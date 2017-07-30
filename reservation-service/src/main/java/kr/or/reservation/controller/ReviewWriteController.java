package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ReservationInfoService;

@Controller
@RequestMapping("/review-write")
public class ReviewWriteController {
	
	private ReservationInfoService reservationService;
	private CommentService commentService;
	
	public ReviewWriteController() {}
	
	@Autowired
	public void setReservationService (ReservationInfoService reservationService) {
		this.reservationService = reservationService;
	}
	
	@Autowired
	public void setCommentService (CommentService commentService) {
		this.commentService = commentService;
	}
	
	
	@GetMapping(path="/{reservationId}")
	public ModelAndView getReservation(@PathVariable int reservationId) {
		ModelAndView model = new ModelAndView("reviewWrite");

		model.addObject("reservationInfo", reservationService.selectById(reservationId));
		return model;
	}
	
	
}
