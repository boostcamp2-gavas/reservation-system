package connect.reservation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.service.ProductService;
import connect.reservation.service.CommentService;

@RestController
@RequestMapping("/productInfo")
public class ProductController {
	private final ProductService productService;
	private final CommentService reservationCommentService;

	final static int productNum = 10;
	
	@Autowired
	public ProductController(
			ProductService productService, 
			CommentService reservationCommentService) {
		this.productService = productService;
		this.reservationCommentService = reservationCommentService;
	}
	
	@GetMapping("/all")
	public Map<String, Object> getAll(@RequestParam("start") Integer start) {
		return productService.getMainInfo(start*productNum);
	}
	
	@GetMapping("/category")
	public Map<String, Object> getCategory(
			@RequestParam("categoryId") Integer categoryId, 
			@RequestParam("start") Integer start) {
		return productService.getCategoryInfo(categoryId, start*productNum);
	}
	
	@GetMapping("/commentImage")
	public Map<String, Object> getCommentImage(@RequestParam("commentId") Integer commentId) {
		return reservationCommentService.getImage(commentId);
	}
	
	
}
