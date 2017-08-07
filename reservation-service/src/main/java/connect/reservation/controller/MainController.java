package connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
=======
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD

import connect.reservation.domain.Category;
import connect.reservation.service.CategoryService;
import connect.reservation.service.ProductInfoService;
import connect.reservation.service.ReservationCommentService;
import connect.reservation.service.ReservationInfoService;
import connect.reservation.service.UsersService;
=======
import org.springframework.web.bind.annotation.ResponseBody;

import connect.reservation.domain.Category;
import connect.reservation.domain.ReservationInfo;
import connect.reservation.domain.ReservationType;
import connect.reservation.domain.User;
import connect.reservation.dto.Product;
import connect.reservation.service.CategoryService;
import connect.reservation.service.CommentService;
import connect.reservation.service.ProductService;
import connect.reservation.service.ReservationService;
import connect.reservation.service.UserService;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7

@Controller
@RequestMapping("/")
public class MainController {
	
<<<<<<< HEAD
	private final CategoryService categoryService;
	private final ProductInfoService productInfoService;
	private final ReservationCommentService reservationCommentService;
	private final UsersService usersService;
	private final ReservationInfoService reservationInfoService;
=======
	@Value("${spring.naverlogin.clientId}")
	private String naverMap;
	
	private final CategoryService categoryService;
	private final ProductService productService;
	private final CommentService commentService;
	private final UserService userService;
	private final ReservationService reservationService;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	
	
	@Autowired
	public MainController(
			CategoryService categoryService, 
<<<<<<< HEAD
			ProductInfoService productInfoService,
			ReservationCommentService reservationCommentService,
			UsersService usersService,
			ReservationInfoService reservationInfoService) {
		this.categoryService = categoryService;
		this.productInfoService = productInfoService;
		this.reservationCommentService = reservationCommentService;
		this.usersService = usersService;
		this.reservationInfoService = reservationInfoService;
=======
			ProductService productService,
			CommentService commentService,
			UserService userService,
			ReservationService reservationService) {
		this.categoryService = categoryService;
		this.productService = productService;
		this.commentService = commentService;
		this.userService = userService;
		this.reservationService = reservationService;
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	}
	
	@GetMapping("/category")
	public String index() {
		return "index";
	}
	
	@GetMapping("/")
	public String mvMain(Model model) {		
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryService.getAll();
		
		model.addAttribute("rolling", productService.getRolling());
		model.addAttribute("category", categoryList);
		
		return "mainpage";
		//return "files";
	}
	
	@GetMapping("/mvMyPage")
<<<<<<< HEAD
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
	
	@PostMapping("/reserve")
	public String add(HttpSession session, HttpServletRequest request, @RequestParam("productId") int productId) {
		// getParameter, getAttribute 차이
		int userId = Integer.parseInt(session.getAttribute("userId")+"");
		String countInfo = request.getParameter("count_info");
		String userName = request.getParameter("name");
		String userTel = request.getParameter("tel");
		String userEmail = request.getParameter("email");
		String reserveDate = request.getParameter("reserve_date");
		
		reservationInfoService.add(productId, userId, countInfo, userName, userTel, userEmail, reserveDate);
		
		return "redirect:/mvMyPage";
=======
	public String mvMyPage(Model model, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		if(null != currentUser) {
			int userId = currentUser.getId();
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
		
		double avg = commentService.getScoreAverage(productId);
		
		model.addAttribute("productId", productId);
		model.addAttribute("productImage", productService.getImage(productId));
		model.addAttribute("detailInfo", productService.getDetail(productId));
		
		model.addAttribute("commentList", commentService.getList(productId, 0, 3));
		model.addAttribute("commentCount", commentService.getCount(productId));
		model.addAttribute("scoreAverage", avg);
		model.addAttribute("starPoint", avg/5.0*100);
		
		model.addAttribute("NoticeImage", productService.getNoticeImage(productId));
		model.addAttribute("InfoImage", productService.getInfoImage(productId));
//		model.addAttribute("naverMap", getNaverMap());
//		System.out.println(getNaverMap());

		return "detail";
	}
	
//	public String getNaverMap(){
//		String clientId = "eGDuy2NMeDv1C1QCsPGF";//애플리케이션 클라이언트 아이디값";
//        String clientSecret = "hw2sty6mby";//애플리케이션 클라이언트 시크릿값";
//        
//        String result = "";
//        try {
//            String addr = URLEncoder.encode("불정로 6", "UTF-8");
//            String apiURL = "https://openapi.naver.com/v1/map/geocode?query=" + addr; //json
//            //String apiURL = "https://openapi.naver.com/v1/map/geocode.xml?query=" + addr; // xml
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("X-Naver-Client-Id", clientId);
//            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            result = response.toString();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return result;
//	}
	
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
		
		List<Product> list = productService.getPriceInfo(productId);
		
		model.addAttribute("reserveInfo", map.get("info"));
		model.addAttribute("startDay", map.get("startDay"));
		model.addAttribute("endDay", map.get("endDay"));
		model.addAttribute("price", list);
		model.addAttribute("minPrice", productService.getMinimunPrice(list));
		model.addAttribute("user", userService.getUserInfo(userId));
		return "reserve";
	}
	
	@PostMapping("/reserve")
	@ResponseBody
	public String addReservation(HttpSession session, @RequestBody ReservationInfo reservationInfo) {
		User currentUser = (User)session.getAttribute("currentUser");

		// getParameter, getAttribute 차이
		reservationInfo.setUserId(currentUser.getId());
		reservationInfo.setReservationDate(userService.getDate());
		reservationInfo.setCreateDate(userService.getDate());
		reservationInfo.setReservationType(ReservationType.REQUESTING);
		
		reservationService.add(reservationInfo);
		return "success";
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	}
}
