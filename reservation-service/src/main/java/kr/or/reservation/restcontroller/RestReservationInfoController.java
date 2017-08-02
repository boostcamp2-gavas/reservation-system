package kr.or.reservation.restcontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.domain.Product;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.ProductService;
import kr.or.reservation.service.ReservationInfoService;
import kr.or.reservation.service.UserReservationService;

@RestController
@RequestMapping(path = "/api/reservation")
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

	@GetMapping()
	public List<UserReservationDTO> getReservationAll(HttpSession session) {
		int id = (Integer)session.getAttribute("id");
		return 	userReservationService.selectReservationAll(id);
	}
	
	@GetMapping("/type/{type}")
	public List<UserReservationDTO> getReservationByType(@PathVariable int type,HttpSession session) {
		int id = (Integer)session.getAttribute("id");
		return 	userReservationService.selectReservationByType(id, type);
	}
	
	@PostMapping
	public Long insert(@ModelAttribute ReservationInfo reservationInfo ){
		log.info(reservationInfo);
		return reservationInfoService.insert(reservationInfo);
	}
	
	
	@DeleteMapping("/{reservationId}")
	public boolean deleteProduct(@PathVariable int reservationId,HttpSession session) {
		int id = (Integer)session.getAttribute("id");
		return userReservationService.cancelReservation(id, reservationId);
		
	}
	



}
