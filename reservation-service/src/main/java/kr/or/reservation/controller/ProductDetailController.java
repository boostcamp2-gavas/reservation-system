package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.reservation.service.CommentService;
<<<<<<< HEAD
import kr.or.reservation.service.ImgService;
=======
import kr.or.reservation.service.ImgFileService;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
import kr.or.reservation.service.ProductService;

@Controller
@RequestMapping(path = "/product")
public class ProductDetailController {

	ProductService productService;
<<<<<<< HEAD
	ImgService imgService;
=======
	ImgFileService imgService;
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
	CommentService commentService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
<<<<<<< HEAD
	public void setImgService(ImgService imgService) {
=======
	public void setImgService(ImgFileService imgService) {
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
		this.imgService = imgService;
	}

	@Autowired
	public void setCommentForDetailService(CommentService commentService) {
		this.commentService = commentService;
	}


	@GetMapping
	@RequestMapping("/detail/{id}")
	public String getProductDetail(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("detail", productService.selectOne(id));
		model.addAttribute("img",imgService.selectList(id));
		model.addAttribute("comment",commentService.selectByProductId(id));
		model.addAttribute("avg",commentService.selectAvgScoreByProductId(id));
		return "detail";
	}
	

}