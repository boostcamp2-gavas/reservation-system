package connect.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import connect.reservation.domain.Users;
import connect.reservation.dto.NaverLoginUser;
import connect.reservation.dto.NaverLoginUserResult;
import connect.reservation.service.UsersService;
import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private String clientId = "eGDuy2NMeDv1C1QCsPGF";
	private String clientSecret = "hw2sty6mby";
	private String callbackUrl = "http://localhost:8080/login/checkState";
	private String state = "";
	private String code = "";
	private String type = "";
	

	@Autowired
	UsersService usersService;
	


	@GetMapping("")
	public String mvLogin(HttpServletRequest request, @RequestParam("type") String type) {
		
		try {
			if(null == type) {
				return null; // 오류처리하기*************************************************************************** 
			}
			this.type = type;
			
			String encodingUrl = URLEncoder.encode(callbackUrl, "utf-8");
			String naverLoginUrl = getNaverLoginUrl(encodingUrl, request.getSession());
			
			return "redirect:" + naverLoginUrl;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ""; // 오류처리하기***************************************************************************
		}
	}
	
	private String getNaverLoginUrl(String encodingUrl, HttpSession session) {
		// 상태 토큰으로 사용할 랜덤 문자열 생성
		state = generateState();
		// 세션 또는 별도의 저장 공간에 상태 토큰을 저장
		session.setAttribute("state", state);
		
		// 네이버 아이디로 로그인 인증 요청문 생성
		String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
							+ "&client_id=" + clientId
							+ "&redirect_uri=" + encodingUrl
							+ "&state=" + state;
		
		return naverLoginUrl;
	}
	
	public String generateState() {
		// CSRF 방지를 위한 상태 토큰 생성 코드
		// 상태 토큰은 추후 검증을 위해 세션에 저장되어야 한다.
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

	public String getAccessToken (String state, String code) {
		String accessTokenUrl = "https://nid.naver.com/oauth2.0/token"
								+ "?client_id=" + clientId
								+ "&client_secret=" + clientSecret
								+ "&grant_type=authorization_code"
								+ "&state=" + state
								+ "&code=" + code;
		
		return accessTokenUrl;
	}
	
	@GetMapping("/checkState")
	public String check(HttpSession session, @RequestParam("code") String code, @RequestParam("state") String state) {
		
		// 세션 또는 별도의 저장 공간에서 상태 토큰을 가져옴
		String storedState = session.getAttribute("state") + "";
		
		// CSRF 방지를 위한 상태 토큰 검증 검증
		// 세션 또는 별도의 저장 공간에 저장된 상태 토큰과 콜백으로 전달받은 state 파라미터의 값이 일치해야 함
		if( !state.equals( storedState ) ) {
			//401 unauthorized
			return "redirect:/login/"; 
		} 
		
		//200 success
		try {
		    this.code = code; 
		    
		    String accessTokenUrl = getAccessToken(state, code);
		    String accessToken = "";
			String refreshToken = "";
			
			RestTemplate tokenRest = new RestTemplate();
			ResponseEntity<JSONObject> tokenResponse = null;
			
			tokenResponse = tokenRest.exchange(accessTokenUrl, HttpMethod.GET, null, JSONObject.class);
			JSONObject obj = tokenResponse.getBody();
			
			accessToken = (String)obj.get("access_token");
			refreshToken = (String)obj.get("refresh_token");
												
			if(accessToken != null) {
				// 접근 토큰 얻으면 사용자 정보 가져옴
				getUserProfile(accessToken, session);
			}
	    }catch (Exception e) {
	      System.out.println(e);
	    }
		
		if("reserve".equals(type))
			return session.getAttribute("beforeUrl")+"";
		else
			return "redirect:/mvMyPage";
	}
	
	public void getUserProfile(String accessToken, HttpSession session) {
		// 사용자 프로필 조회
		String profileUrl = "https://openapi.naver.com/v1/nid/me";
        String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가
    	
    	RestTemplate profileRest = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", header);				
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		
		ResponseEntity<NaverLoginUserResult> responseEntity = profileRest.exchange(profileUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<NaverLoginUserResult>(){});	
		
		if(responseEntity.getBody().getMessage() != "success"){
			session.setAttribute("loginOk", "false");
			// 에러발생
		}
		
		NaverLoginUser snsUser = responseEntity.getBody().getResponse();
		String snsId = snsUser.getId();
		Users user = usersService.getSnsUser(snsId);
		
		if(user == null) {
			// 가입 기록이 없으면 user 추가
			usersService.addSnsUser(snsUser);
			user = usersService.getSnsUser(snsId);
		}
		else {
			// 정보업데이트 있을 경우 DB 업데이트
			String nickname = snsUser.getNickname();
			String profile = snsUser.getProfileImage();
			
			if(!nickname.equals(user.getNickname()) || !profile.equals(user.getSnsProfile())) {
				usersService.updateSnsUser(snsId, nickname, profile);
			}
		}
		// 가입된 user면 session 설정
		session.setAttribute("loginOk", "true");
		session.setAttribute("userId", user.getId());
		session.setAttribute("userName", user.getUsername());
	}
}
