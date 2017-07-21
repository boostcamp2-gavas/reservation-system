package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ImgService;
import kr.or.reservation.service.ProductService;

@Controller
@RequestMapping(path = "/product")
public class ProductDetailController {

	ProductService productService;
	ImgService imgService;
	CommentService commentForDetailService;
	
	@Autowired
	public ProductDetailController(ProductService productService, 
			ImgService imgService,CommentService commentForDetailService) {
		this.productService = productService;
		this.imgService = imgService;
		this.commentForDetailService =commentForDetailService;
	}

	@GetMapping
	@RequestMapping("/detail/{id}")
	public String getProductDetail(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("detail", productService.selectOne(id));
		model.addAttribute("img",imgService.selectList(id));
		model.addAttribute("comment",commentForDetailService.selectByProductId(id));
		model.addAttribute("avg",commentForDetailService.selectAvgScoreByProductId(id));
		return "detail";
	}

}