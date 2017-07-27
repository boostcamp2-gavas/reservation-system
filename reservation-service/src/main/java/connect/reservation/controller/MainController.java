package connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import connect.reservation.domain.Category;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.domain.User;
import connect.reservation.service.CategoryService;
import connect.reservation.service.ProductService;
import connect.reservation.service.ReservationCommentService;
import connect.reservation.service.ReservationService;
import connect.reservation.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	
	private final CategoryService categoryService;
	private final ProductService productService;
	private final ReservationCommentService reservationCommentService;
	private final UserService userService;
	private final ReservationService reservationService;
	
	
	@Autowired
	public MainController(
			CategoryService categoryService, 
			ProductService productService,
			ReservationCommentService reservationCommentService,
			UserService userService,
			ReservationService reservationService) {
		this.categoryService = categoryService;
		this.productService = productService;
		this.reservationCommentService = reservationCommentService;
		this.userService = userService;
		this.reservationService = reservationService;
	}
	
	@GetMapping("/category")
	public String index() {
		return "index";
	}
	
	@GetMapping("/")
	public String mvMain(Model model) {		
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryService.getAll();
		
		model.addAttribute("category", categoryList);
		
		return "mainpage";
		//return "files";
	}
	
	@GetMapping("/mvMyPage")
	public String mvMyPage(Model model, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		if(null != currentUser) {
			int userId = 10;
			//session.setAttribute("userId", 10);	// 지우기
			model.addAttribute("reservation", reservationService.get(userId));
			model.addAttribute("reservationStatus", reservationService.getCount(userId));
			return "myreservation";
		}
		else 
			return "redirect:/login?type=myPage";

	}

	@GetMapping("/mvDetail")
	public String mvDetail(Model model, @RequestParam("productId") Integer productId) {
		if(productId < 1)
			return null;
		
		model.addAttribute("productId", productId);
		model.addAttribute("productImage", productService.getImage(productId));
		model.addAttribute("detailInfo", productService.getDetail(productId));
		model.addAttribute("commentMap", reservationCommentService.getList(productId));
		model.addAttribute("NoticeImage", productService.getNoticeImage(productId));
		model.addAttribute("InfoImage", productService.getInfoImage(productId));
		
		return "detail";
	}
	
	@GetMapping("/reserve")
	public String mvReserve(HttpSession session, Model model, @RequestParam("productId") Integer productId) {
		if(productId < 1)
			return null;
		
		User currentUser = (User)session.getAttribute("currentUser");
		
		if(null == currentUser) {
			session.setAttribute("beforeUrl", "redirect:/reserve?productId="+productId);
			return "redirect:/login?type=reserve";
		}
		
		int userId = currentUser.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = productService.getReserveInfo(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("reserveInfo", map.get("info"));
		model.addAttribute("startDay", map.get("startDay"));
		model.addAttribute("endDay", map.get("endDay"));
		model.addAttribute("price", productService.getPriceInfo(productId));
		model.addAttribute("user", userService.getUserInfo(userId));
		return "reserve";
	}
	
	@PostMapping("/reserve")
	public String add(HttpSession session, @RequestParam("productId") Integer productId, ReservationInfo reservationInfo) {
		System.out.println("controller");
		if(productId < 1)
			return null;
		
		User currentUser = (User)session.getAttribute("currentUser");
		
		// getParameter, getAttribute 차이
		int userId = currentUser.getId();
		reservationInfo.setUserId(userId);
		reservationInfo.setReservationType(0);

		System.out.println(reservationInfo.getReservationDate());
		
		//reservationService.add(productId, userId, countInfo, userName, userTel, userEmail, reserveDate);
		
		return "redirect:/mvMyPage";
	}
}
