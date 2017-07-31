package connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import connect.reservation.domain.Product;
import connect.reservation.domain.ReservationType;
import connect.reservation.domain.ReservationUserComment;
import connect.reservation.domain.User;
import connect.reservation.dto.UploadFile;
import connect.reservation.service.CommentService;
import connect.reservation.service.FileService;
import connect.reservation.service.ProductService;

@Controller
@RequestMapping("/comments")
public class CommentController {
	
	private final CommentService commentService;
	private final FileService fileService;
	private final ProductService productService;
	
	static final String FILE_PATH = "/apps/apache-tomcat-8.5.16/webapps/";
	
	@Autowired
	public CommentController(CommentService commentService, FileService fileService, ProductService productService) {
		this.commentService = commentService;
		this.fileService = fileService;
		this.productService = productService;
	}
	
	@GetMapping("/{productId}")
	public String mvReviewList(HttpSession session, Model model, @PathVariable int productId, @RequestParam int start, @RequestParam int end) {
		User currentUser = (User) session.getAttribute("currentUser");
		model.addAttribute("reviewList", commentService.getList(productId));
		System.out.println(ReservationType.valueOf("REFUND_CANCEL"));
		return "review";
	}

	@GetMapping("/write")
	public String mvWrite(Model model, @RequestParam("reservationId") int reservationId) {
		model.addAttribute("reserveName", commentService.getName(reservationId).getName());
		model.addAttribute("reservationId", reservationId);
		Product product = new Product();
		product = commentService.getName(reservationId);
		
		model.addAttribute("productId", product.getId());
		model.addAttribute("productName", product.getName());
		return "reviewWrite";
	}
	
	@PostMapping("/write")
	@ResponseBody
	public String add(
			HttpSession session,
			@RequestParam(name="commentImg", required=false) MultipartFile[] files,
			@RequestParam("productId") int productId,
			@RequestParam("comment") String comment, 
			@RequestParam("score") int score) {

		User currentUser = (User) session.getAttribute("currentUser");

		ReservationUserComment commentDomain = new ReservationUserComment();
		commentDomain.setProductId(productId);		
		commentDomain.setUserId(currentUser.getId());
		commentDomain.setScore(score);
		commentDomain.setComment(comment);
		
		int commentId = commentService.add(commentDomain);
		
		if(null != files) {
			UploadFile uploadFile = new UploadFile();
			uploadFile.setUserId(currentUser.getId());
			uploadFile.setCommentId(commentId);
			uploadFile.setPath(FILE_PATH);
			uploadFile.setContentType("한줄평이미지");
			
			fileService.uploadFile(files, uploadFile);
		}
		return "success";
	}
}
