package kr.or.reservation.restcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Comment;
import kr.or.reservation.service.CommentService;

@RestController
public class RestCommentController {

	CommentService commentService;
	
	@Autowired
	public RestCommentController(CommentService commentForDetailService) {
		this.commentService =commentForDetailService;
	}
	
	Logger log = Logger.getLogger(this.getClass());
	
	
	@GetMapping
	@RequestMapping("/commentImg/{id}")
	public List<?> selectAll(@PathVariable(name = "id") int commentId){
		// 타입 체크 할것
		return commentService.getFileIdByCommentId(commentId);
	}
	
	@PostMapping("/comment")
	@ResponseBody
	public long insert(@ModelAttribute Comment comment ){
		return commentService.insert(comment);
	}

}
