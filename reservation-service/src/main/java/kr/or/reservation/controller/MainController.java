package kr.or.reservation.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.api.NaverLogin;


@Controller
@PropertySource("classpath:/application.properties")
public class MainController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Value("${spring.naver.apikey}")
	private String CLIENT_ID;
	@Value("${spring.naver.apisecretkey}")
	private String SECRET_ID;
	
	
    @GetMapping(path = "/")
    public String viewMain(Model model,HttpSession session){
    	// session 이 존재하면, mypageURI를 줌 
    	// 이 부분 여러 페이지에서 사용될 수 있으니, 함수로 따로 뺄까 ? 
    	if(session.getAttribute("id") !=null) {
    		model.addAttribute("loginURL", "/my");
    	}else {
    		NaverLogin login = new NaverLogin();
    		login.setCLIENT_ID(CLIENT_ID);
    		login.setSECRET_ID(SECRET_ID);
    		String url = login.getLoginURL(session);
        	model.addAttribute("loginURL", url);
    	}
    	return "mainpage";
    }
 
}