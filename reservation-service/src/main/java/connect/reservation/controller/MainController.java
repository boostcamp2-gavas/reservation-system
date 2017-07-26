package connect.reservation.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import connect.reservation.domain.Category;
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
	public String mvMyPage(HttpSession session) {
		if(null == session.getAttribute("loginOk"))
			return "redirect:/login?type=myPage";
		else {
			session.setAttribute("userId", 9);	// 지우기
			return "myreservation";
		}
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
		
		if(null == session.getAttribute("loginOk")) {
			session.setAttribute("beforeUrl", "redirect:/reserve?productId="+productId);
			return "redirect:/login?type=reserve";
		}
		
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		
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
	public String add(HttpSession session, HttpServletRequest request, @RequestParam("productId") Integer productId) {
		if(productId < 1)
			return null;
		
		// getParameter, getAttribute 차이
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		String countInfo = request.getParameter("count_info");
		String userName = request.getParameter("name");
		String userTel = request.getParameter("tel");
		String userEmail = request.getParameter("email");
		System.out.println(request.getParameter("reserve_date"));
		Timestamp reserveDate = java.sql.Timestamp.valueOf("reserve_date");
		
		reservationService.add(productId, userId, countInfo, userName, userTel, userEmail, reserveDate);
		
		return "redirect:/mvMyPage";
	}
}
