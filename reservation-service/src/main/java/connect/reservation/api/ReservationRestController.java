package connect.reservation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import connect.reservation.domain.ReservationType;
import connect.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
	
	private ReservationService reservationService;
	
	@Autowired
	public ReservationRestController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	
	@PutMapping("/{id}")
	public int modify(@PathVariable int id, @RequestParam("type") ReservationType reservationType) {
		if(id < 0){
			return 0;
		}
		return reservationService.modify(id, reservationType.ordinal());
	}
}
