package connect.reservation.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.service.ProductService;
import connect.reservation.dto.ReservationComment;
import connect.reservation.service.CommentService;

@RestController
@RequestMapping("/api/productInfo")
public class ProductRestController {
	private final ProductService productService;
	private final CommentService commentService;

	final static int PRODUCT_NUM = 10;
	
	@Autowired
	public ProductRestController(
			ProductService productService, 
			CommentService commentService) {
		this.productService = productService;
		this.commentService = commentService;
	}
	
	@GetMapping("/all")
	public Map<String, Object> getAll(@RequestParam("start") Integer start) {
		return productService.getMainInfo(start*PRODUCT_NUM);
	}
	
	@GetMapping("/category")
	public Map<String, Object> getCategory(
			@RequestParam("categoryId") Integer categoryId, 
			@RequestParam("start") Integer start) {
		return productService.getCategoryInfo(categoryId, start*PRODUCT_NUM);
	}
	
	@GetMapping("/commentImage")
	public List<ReservationComment> getCommentImage(@RequestParam("commentId") Integer commentId) {
		return commentService.getImage(commentId);
	}
	
}
