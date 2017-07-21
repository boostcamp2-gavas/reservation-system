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
import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ProductService;

@RestController
public class RestCommentController {

	CommentService commentForDetailService;
	
	@Autowired
	public RestCommentController(CommentService commentForDetailService) {
		this.commentForDetailService =commentForDetailService;
	}
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	@GetMapping
	@RequestMapping("/commentImg/{id}")
	public List<?> selectAll(@PathVariable(name = "id") int commentId){
		// 타입 체크 할것
		return commentForDetailService.getFileIdByCommentId(commentId);
	}


}
