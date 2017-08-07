package kgw.reservation.controller.review;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kgw.reservation.domain.FileDomain;
import kgw.reservation.domain.ReservationUserComment;
import kgw.reservation.domain.User;
import kgw.reservation.dto.Criteria;
import kgw.reservation.dto.UserCommentWrapper;
import kgw.reservation.exception.MismatchJpegPngFormatException;
import kgw.reservation.security.LogginedUser;
import kgw.reservation.service.FileService;
import kgw.reservation.service.ReservationInfoService;
import kgw.reservation.service.UserCommentService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Value("${USER_DIR}")
	private String DIRNAME;
	
	private UserCommentService userCommentService;
	private FileService fileService;
	private ReservationInfoService reservationInfoService;
	private final Logger log = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	public ReviewController(UserCommentService userCommentService, FileService fileService,
			ReservationInfoService reservationInfoService) {
		this.userCommentService = userCommentService;
		this.fileService = fileService;
		this.reservationInfoService = reservationInfoService;
	}

	@GetMapping("/form")
	public String form(@RequestParam Integer productId, HttpSession session) {
		User user = (User) session.getAttribute("loginInfo");
		Integer count = reservationInfoService.findCountByUserIdAndProductId(productId, user.getId());
		if(count==0) {
			return "error";
		}
		return DIRNAME + "/reviewWrite";
	}
	//@RequestParam UserComment userComment, @RequestParam List<Integer> fileIdList,
	@PostMapping("/form")
	public String makeReivew(@ModelAttribute @Valid ReservationUserComment reservationUserComment,
				@RequestParam (required=false) List<Integer> fileIdList, HttpSession session) {
		log.info("========userComment info ========");
		log.info(reservationUserComment.getComment());
		log.info(reservationUserComment.getProductId());
		log.info(""+reservationUserComment.getId());
	
		User user = (User) session.getAttribute("loginInfo");
		Integer userId = user.getId();

		reservationUserComment.setUserId(userId);
		
		Integer commentId = userCommentService.createReservationUserComment(reservationUserComment, fileIdList);
		if(commentId==null){
			log.info("===== comment 등록 실패 =====");
			return "/error";
		}else{
			log.info("===== comment 등록 성공 =====");
		}
		return "redirect:/users";
	}

	@DeleteMapping("/api/images/{fileId}")
	@ResponseBody
	public Integer removeUserCommentImage(@PathVariable Integer fileId ){
		log.info("fileId ====:"+fileId);
		return userCommentService.removeUserCommentImagefile(fileId);
	}
	
	@PostMapping("/api/images")
	@ResponseBody		
	public List<FileDomain> createUserCommentImages(MultipartFile[] images, @LogginedUser User user) {
		log.info("{}",images);
		Integer userId = user.getId();
		for(int i=0; i<images.length; i++){
			MultipartFile mpf  = images[i];
			if(!mpf.getContentType().equals("image/jpeg")&&!mpf.getContentType().equals("image/png")){
				log.info("==========data is not right format ===========");
				throw new MismatchJpegPngFormatException("이미지가 jpeg/png 형식이 아닙니다.");
			}		
		}
		return fileService.createFileList(userId, images);
	}
	@GetMapping
 	public String reviewView(@RequestParam Integer productId) {
 		return DIRNAME+ "/review";
 	}
	 
 	@GetMapping("/api")
 	@ResponseBody
 	public UserCommentWrapper getList(@RequestParam Integer productId, @ModelAttribute Criteria criteria) {
 	log.debug("{}", criteria);
 	return userCommentService.getCommentListByProductId(productId, criteria.getOffset(), criteria.getSize());
	}
}