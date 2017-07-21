package kr.or.reservation.restController;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Product;
import kr.or.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class RestProductController {

	@Autowired
	ProductService productService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	// get Query 에 대하여       
	// 그냥 쿼리를 쓰는 방향으로 ....  ~ 
	@GetMapping
	@RequestMapping("/category/{categoryId}/start/{start}")
	public List<Product> selectAll(@PathVariable(name = "categoryId") int categoryId,
			@PathVariable(name = "start") int start){
		List<Product> list = productService.getProductByCategory(start,categoryId);
		return list;
	}

	@GetMapping
	@RequestMapping("/counter/{categoryId}")
	public int countProduct(@PathVariable(name= "categoryId") int categoryId){
    	return productService.countProduct(categoryId);
	}
	
	@GetMapping
	@RequestMapping("/counter")
	public int countProductAll(){
    	return productService.countProduct(0);
	}


}
