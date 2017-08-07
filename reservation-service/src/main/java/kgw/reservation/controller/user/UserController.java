package kgw.reservation.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kgw.reservation.domain.User;
import kgw.reservation.dto.MyReservation;
import kgw.reservation.dto.MyReservationCount;
import kgw.reservation.security.LogginedUser;
import kgw.reservation.service.ReservationInfoService;
import kgw.reservation.sql.ReservationInfoSqls;

@Controller
@RequestMapping("/users")
public class UserController {
	@Value("${USER_DIR}")
	private String DIRNAME;
	private ReservationInfoService reservationInfoService;
	
	@Autowired
	public UserController(ReservationInfoService reservationInfoService) {
		this.reservationInfoService = reservationInfoService;
	}
	
	@GetMapping
	public String myPage(Model model) {
		model.addAttribute("asking", ReservationInfoSqls.ASKING);
		model.addAttribute("confirm", ReservationInfoSqls.CONFIRM);
		model.addAttribute("completion", ReservationInfoSqls.COMPLETION);
		model.addAttribute("cancellation", ReservationInfoSqls.CANCELLATION);
		model.addAttribute("refund", ReservationInfoSqls.REFUND);
		
		return DIRNAME+"/myreservation";
	}
	
	@GetMapping("/reservation")
	@ResponseBody
	public List<MyReservation> getAllReservationList(@RequestParam(required=false) Integer type, @LogginedUser User user) {
		if(type==null) {
			return reservationInfoService.findAllReservationByUserId(user.getId());
		}

		return reservationInfoService.findReservationByUserIdAndType(user.getId(), type);
	}
	
	@GetMapping("/reservation/count")
	@ResponseBody
	public MyReservationCount getReservationCount(@LogginedUser User user) {
		return reservationInfoService.countAllReservationByUserId(user.getId());
	}
	
	@DeleteMapping("/reservation/{reservationType}")
	@ResponseBody
	public Integer removeReservationById(@PathVariable Integer reservationType) {
		return reservationInfoService.removeReservationById(reservationType);
	}
	
	@PutMapping("/reservation/cancellation/{reservationId}")
	@ResponseBody
	public Integer cancelReservationById(@PathVariable Integer reservationId) {
		return reservationInfoService.modifyTypeById(reservationId, ReservationInfoSqls.CANCELLATION);
	}
}
