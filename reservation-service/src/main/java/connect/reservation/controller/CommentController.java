package connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import connect.reservation.domain.ReservationInfo;
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
		return "reviewWrite";
	}
	
	@PostMapping("/write")
	public String add(Model model, @RequestBody ReservationInfo reservationInfo) {
		
		return "review";
	}
}
