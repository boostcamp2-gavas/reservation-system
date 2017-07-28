package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.serviceImpl.CommentServiceImpl;
import kr.or.reservation.serviceImpl.ReservationInfoServiceImpl;

@Controller
@RequestMapping("/review-write")
public class ReviewWriteController {
	
	private ReservationInfoServiceImpl reservationService;
	private CommentServiceImpl commentService;
	
	public ReviewWriteController() {}
	
	@Autowired
	public void setReservationService (ReservationInfoServiceImpl reservationService) {
		this.reservationService = reservationService;
	}
	
	@Autowired
	public void setCommentService (CommentServiceImpl commentService) {
		this.commentService = commentService;
	}
	
	
	@GetMapping(path="/{reservationId}")
	public ModelAndView getReservation(@PathVariable int reservationId) {
		ModelAndView model = new ModelAndView("reviewWrite");
		model.addObject("reservation", reservationService.selectById(reservationId));
		return model;
	}
	
	
}
