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
import kr.or.reservation.service.ReservationService;

@Controller
@RequestMapping(path = "/product")
public class ReservationController {

	ReservationService reservationService;
	
	@Autowired
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	@RequestMapping("/reservation/{id}")
	public String getProductDetail(Model model, @PathVariable(name = "id") int productId) {
		model.addAttribute("reservation",reservationService.selectOne(productId));
		return "reserve";
	}
	
	
	

}