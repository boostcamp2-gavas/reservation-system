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

	final static int productNum = 10;
	
	@Autowired
	public ProductRestController(
			ProductService productService) {
		this.productService = productService;
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
	

}
