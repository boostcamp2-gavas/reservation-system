package connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import connect.reservation.domain.Product;
import connect.reservation.domain.ReservationUserComment;
import connect.reservation.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService reservationCommentService;
	
	@Autowired
	public CommentController(CommentService reservationCommentService) {
		this.reservationCommentService = reservationCommentService;
	}

	@GetMapping("/write")
	public String mvWrite(Model model, @RequestParam("reservationId") int reservationId) {
		model.addAttribute("reserveName", reservationCommentService.getName(reservationId));
		model.addAttribute("reservationId", reservationId);
		Product product = new Product();
		product = reservationCommentService.getName(reservationId);
		
		model.addAttribute("productId", product.getId());
		model.addAttribute("productName", product.getName());
		
		return "reviewWrite";
	}
	
	@PostMapping("/write")
	//public String add(Model model, @RequestBody ReservationUserComment reservationUserComment) {
	public String add(HttpSession session, Model model) 	{
		
		ReservationUserComment comment = new ReservationUserComment();
		comment.setProductId(0);
		
		return "review";
	}
}
