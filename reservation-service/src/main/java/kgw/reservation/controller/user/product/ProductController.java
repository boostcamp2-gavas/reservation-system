package kgw.reservation.controller.user.product;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kgw.reservation.dao.ProductDao;
import kgw.reservation.domain.ReservationInfo;
import kgw.reservation.domain.User;
import kgw.reservation.dto.ProductInfo;
import kgw.reservation.service.ProductService;
import kgw.reservation.service.ReservationInfoService;

@Controller
@RequestMapping("/products")
public class ProductController {
	private ProductService productService;
	private ReservationInfoService reservationInfoService;
	@Value("${USER_DIR}")
	private String DIRNAME;
	private static final String url = "/products";
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	public ProductController(ProductService productService, ReservationInfoService reservationInfoService) {
		this.productService = productService;
		this.reservationInfoService = reservationInfoService;
	}
	
	@GetMapping("/{id}")
	public String detailPage(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.findProductDetail(id));
		model.addAttribute("reservationUrl", url+"/reservation/"+id);
		
		return DIRNAME+"/detail";
	}
	
	@GetMapping("/reservation/{id}")
	public String reserveForm(@PathVariable Integer id, Model model) {
		model.addAttribute("productReservation", productService.findProductReservation(id));
		model.addAttribute("url", url+"/reservation"+"/"+id);

		return DIRNAME+"/reserve";
	}
	
	@PostMapping("/reservation/{id}")
	public String reserve(@PathVariable Integer id, Model model, ReservationInfo reservationInfo, HttpSession session) {
		User loginUser = (User)session.getAttribute("loginInfo");
		
		reservationInfo.setProductId(id);
		reservationInfo.setUserId(loginUser.getId());
		//임시		
		reservationInfo.setReservationDate(new Date());
		log.info("postInfo : {}",reservationInfo);
		
		reservationInfoService.create(reservationInfo);
		
		return "redirect:/users/"+loginUser.getId();
	}
	
	@GetMapping
	@ResponseBody
	public List<ProductInfo> readList(@RequestParam(required=false) Integer categoryId, @RequestParam(required=false) Integer offset, @RequestParam(required=false) Integer size) {
		if(offset == null || size == null ) {
			offset = 0;
			size = 10;
		}
		if(categoryId != null)
			return productService.findByCategoryLimit(categoryId, offset, size);
		
		return productService.findAllLimit(offset, size);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer countList(@RequestParam(required=false) Integer categoryId) {
		if(categoryId != null)
			return productService.countByCategory(categoryId);
		return productService.countAll();
	}
	
	
}
