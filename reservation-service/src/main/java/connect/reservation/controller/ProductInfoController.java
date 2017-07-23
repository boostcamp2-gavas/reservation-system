package connect.reservation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.dto.ProductInfo;
import connect.reservation.dto.ReservationComment;
import connect.reservation.service.ProductInfoService;
import connect.reservation.service.ReservationCommentService;

@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {
	private final ProductInfoService productInfoService;
	private final ReservationCommentService reservationCommentService;

	final static int productNum = 10;
	
	@Autowired
	public ProductInfoController(ProductInfoService productInfoService, ReservationCommentService reservationCommentService) {
		this.productInfoService = productInfoService;
		this.reservationCommentService = reservationCommentService;
	}
	
	@GetMapping("/all/{start}")
	public Map<String, Object> getAll(@PathVariable int start) {

		return productInfoService.getMainInfo(start*productNum);
	}
	
	@GetMapping("/category/{categoryId}/start/{start}")
	public Map<String, Object> getCategory(@PathVariable(name = "categoryId") int categoryId, @PathVariable(name = "start") int start) {
		return productInfoService.getCategoryInfo(categoryId, start*productNum);
	}

//	@GetMapping("/commentImage?commentId={commentId}")
//	public List<ReservationComment> getCommentImage(@PathVariable int commentId) {
//		return reservationCommentService.getImageList(commentId);
//	}
	
//	@GetMapping("/commentImage?commentId={commentId}")
	@GetMapping("/commentImage/commentId={commentId}")
	public Map<String, Object> getCommentImage(@PathVariable int commentId) {
		return reservationCommentService.getImage(commentId);
	}
}
