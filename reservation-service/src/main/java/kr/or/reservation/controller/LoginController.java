package kr.or.reservation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	// 로그 선언
	Logger log = Logger.getLogger(this.getClass());

	final static String CLIENT_ID = "w0YSpFZqo6SXUXy5itSy";
	final static String REDIRECT_URL = "http://10.81.25.154/callback";
	final static String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id="
			+ "&redirect_uri=" + "&state=";
	final static String SECRET_ID = "IxSbeRZI3A";

	
	@GetMapping(path = "/login")
	public String viewMain(Model model, HttpSession session) {
		String redirectUrl = "";
		SecureRandom random = null;
		String state = "", apiURL = "";
		try {
			random = new SecureRandom();
			redirectUrl = URLEncoder.encode("YOUR_CALLBACK_URL", "UTF-8");
			state = new BigInteger(130, random).toString();
			apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
			apiURL += "&client_id=" + CLIENT_ID;
			apiURL += "&redirect_uri=" + REDIRECT_URL;
			apiURL += "&state=" + state;
			
			log.info(apiURL);
			session.setAttribute("state", state);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiURL;
	}
	
	@GetMapping(path = "/callback")
	public String enrollSession(Model model, HttpSession session, @RequestParam String code,
			@RequestParam String state) {
		int responseCode = 0;
		String redirectUrl = "", apiURL = "", inputLine = null;
		BufferedReader br = null;
		HttpURLConnection con = null;
		URL url = null;
		log.info("code  :: "+code);
		log.info("state  :: "+state);
		
		if(!state.equals(session.getAttribute("state"))) {
			// 실행 되면 안됌 
		}else {
			try {
				redirectUrl = URLEncoder.encode(REDIRECT_URL, "UTF-8");
				apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
				apiURL += "client_id=" + CLIENT_ID;
				apiURL += "&client_secret=" + SECRET_ID;
				apiURL += "&redirect_uri=" + redirectUrl;
				apiURL += "&code=" + code;
				apiURL += "&state=" + state;
				log.info("apiURL :: " + apiURL);

				try {
					url = new URL(apiURL);
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					responseCode = con.getResponseCode();
					log.info("responseCode=" + responseCode);
					if (responseCode == 200) { // 정상 호출
						br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					} else { // 에러 발생
						br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
					}

					StringBuffer res = new StringBuffer();
					while ((inputLine = br.readLine()) != null) {
						res.append(inputLine);
					}
					br.close();
					if (responseCode == 200) {
						log.info("성공");
					}
					
					JSONObject jsonObj = StringToJsonParser(res.toString());
					// 이러면 response 에 토큰을 줌 
					log.info("토큰 : "+jsonObj.get("access_token"));

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 자원 반환
					if(con != null) {
						con.disconnect();
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return "redirect:/";
	}
	
	
	private JSONObject StringToJsonParser(String str) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}
	
}