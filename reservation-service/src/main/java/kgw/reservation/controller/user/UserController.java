package kgw.reservation.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
	@Value("${USER_DIR}")
	private String DIRNAME;
	public UserController() {
	}
	
	@RequestMapping("/{id}")
	public String myPage(@PathVariable String id, HttpSession session) {
		return DIRNAME+"/myreservation";
	}
	
}
