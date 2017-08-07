package connect.reservation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.dto.ReservationComment;
import connect.reservation.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
	
	private final CommentService commentService;
	
	@Autowired
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/{productId}")
	public List<ReservationComment> get(@PathVariable int productId, @RequestParam int start){
		if(productId < 1){
			return null;
		}
		return commentService.getList(productId, start*10, 10);
	}
	
	@GetMapping("/image")
	public List<ReservationComment> getCommentImage(@RequestParam("commentId") Integer commentId) {
		return commentService.getImage(commentId);
	}
}
