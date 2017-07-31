package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.service.CommentService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private CommentService commentService;
	
	@Autowired
	public void setCommentService (CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping(path="/{productId}")
	public ModelAndView getReservation(@PathVariable int reservationId) {
		ModelAndView model = new ModelAndView("review");
		return model;
	}

}
