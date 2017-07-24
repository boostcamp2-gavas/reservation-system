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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import connect.reservation.domain.Category;
import connect.reservation.service.CategoryService;
import connect.reservation.service.ProductInfoService;
import connect.reservation.service.ReservationCommentService;
import connect.reservation.service.UsersService;

@Controller
@RequestMapping("/")
public class MainController {
	
	private final CategoryService categoryService;
	private final ProductInfoService productInfoService;
	private final ReservationCommentService reservationCommentService;
	private final UsersService usersService;
	
	
	@Autowired
	public MainController(
			CategoryService categoryService, 
			ProductInfoService productInfoService,
			ReservationCommentService reservationCommentService,
			UsersService usersService) {
		this.categoryService = categoryService;
		this.productInfoService = productInfoService;
		this.reservationCommentService = reservationCommentService;
		this.usersService = usersService;
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
		// 로그인을 하지 않은 유저는 로그인 페이지로
		// 로그인 한 후라면 "나의 예약 메인"페이지로 이동한다
		if(!("true").equals(session.getAttribute("loginOk")))
			return "redirect:/login?type=myPage";
		else
			return "myreservation";
	}

	@GetMapping("/mvDetail/{productId}")
	public String mvDetail(Model model, @PathVariable int productId) {
		model.addAttribute("productId", productId);
		model.addAttribute("productImage", productInfoService.getImage(productId));
		model.addAttribute("detailInfo", productInfoService.getDetail(productId));
		model.addAttribute("commentMap", reservationCommentService.getList(productId));
		model.addAttribute("NoticeImage", productInfoService.getNoticeImage(productId));
		model.addAttribute("InfoImage", productInfoService.getInfoImage(productId));
		
		return "detail";
	}
	
	@GetMapping("/reserve")
	public String mvReserve(HttpSession session, Model model, @RequestParam("productId") int productId) {
		if(!("true").equals(session.getAttribute("loginOk"))) {
			session.setAttribute("beforeUrl", "redirect:/reserve?productId="+productId);
			return "redirect:/login?type=reserve";
		}
		
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = productInfoService.getReserveInfo(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("reserveInfo", map.get("info"));
		model.addAttribute("startDay", map.get("startDay"));
		model.addAttribute("endDay", map.get("endDay"));
		model.addAttribute("price", productInfoService.getPriceInfo(productId));
		model.addAttribute("user", usersService.getUserInfo(userId));
		return "reserve";
	}
}
