package kr.or.reservation.restController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Product;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.service.ProductService;
import kr.or.reservation.service.ReservationInfoService;
import kr.or.reservation.service.UserReservationService;

@RestController
@RequestMapping(path = "/reservation")
public class RestReservationInfoController {

	ReservationInfoService reservationInfoService;
	UserReservationService userReservationService;
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	protected void setReservationInfoService(ReservationInfoService reservationInfoService) {
		this.reservationInfoService = reservationInfoService;
	}

	@Autowired
	public void setUserReservationService(UserReservationService userReservationService) {
		this.userReservationService = userReservationService;
	}




	@PostMapping
	public Long insert(@ModelAttribute ReservationInfo reservationInfo ){
		log.info(reservationInfo);
		return reservationInfoService.insert(reservationInfo);
	}
	
	
	// RestController에 Session 을 사용해도 되나요 ? 
	@DeleteMapping("/{productId}")
	public boolean deleteProduct(@PathVariable int reservationId,HttpSession session) {
		int id = (Integer)session.getAttribute("id");
		
		return userReservationService.cancelReservation(id, reservationId);
		
	}


}
