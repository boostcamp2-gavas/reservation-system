package kr.or.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.service.ReservationInfoService;

@Controller
public class ReviewWriteController {

	private ReservationInfoService reservationService;

	public ReviewWriteController() {
	}

	@Autowired
	public void setReservationService(ReservationInfoService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping(path = "/product/{reservationId}/review-write")
	public ModelAndView getReservation(@PathVariable int reservationId) {
		ModelAndView model = new ModelAndView("reviewWrite");

		model.addObject("reservationInfo", reservationService.selectById(reservationId));
		return model;
	}

}
