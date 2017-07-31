package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.domain.AVGForComment;
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
	public String getReservation(Model model, @PathVariable int productId) {
		AVGForComment info = commentService.selectAvgScoreByProductId(productId);
		Float avgScore = info.getAvgScore();
		Long count = info.getAmountOfCount();
		
		model.addAttribute("avgScore", avgScore);
		model.addAttribute("count", count);
		model.addAttribute("productId", productId);
		return "review";
	}

}
