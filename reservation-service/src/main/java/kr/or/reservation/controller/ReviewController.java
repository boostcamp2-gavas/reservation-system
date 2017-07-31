package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.domain.AVGForComment;
import kr.or.reservation.dto.ProductDetailDTO;
import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ProductService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private CommentService commentService;
	private ProductService productService;
	
	@Autowired
	public void setCommentService (CommentService commentService) {
		this.commentService = commentService;
	}
	
	@Autowired
	public void setProductService (ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(path="/{productId}")
	public String getReservation(Model model, @PathVariable int productId) {
		AVGForComment info = commentService.selectAvgScoreByProductId(productId);
		Float avgScore = info.getAvgScore();
		Long count = info.getAmountOfCount();
		ProductDetailDTO product = productService.selectOne(productId);
		
		
		
		model.addAttribute("avgScore", avgScore);
		model.addAttribute("count", count);
		model.addAttribute("productId", productId);
		model.addAttribute("productName", product.getName());
		
		return "review";
	}

}
