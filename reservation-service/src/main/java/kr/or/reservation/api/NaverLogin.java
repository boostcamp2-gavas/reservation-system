package kr.or.reservation.api;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.reservation.dto.NaverUserDTO;

public class NaverLogin {
	final static String CLIENT_ID = "w0YSpFZqo6SXUXy5itSy";
	final static String REDIRECT_URL = "http://localhost/callback";
	final static String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id="
			+ "&redirect_uri=" + "&state=";
	final static String SECRET_ID = "IxSbeRZI3A";

	// Logger 설정

	Logger log = Logger.getLogger(this.getClass());

	public String getLoginURL(HttpSession session) {
		String redirectUrl = "";
		SecureRandom random = null;
		String state = "", apiURL = "";
		try {
			random = new SecureRandom();
			redirectUrl = URLEncoder.encode(REDIRECT_URL, "UTF-8");
			state = new BigInteger(130, random).toString();
			apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
			apiURL += "&client_id=" + CLIENT_ID;
			apiURL += "&redirect_uri=" + REDIRECT_URL;
			apiURL += "&state=" + state;

			session.setAttribute("state", state);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiURL;
	}

	public JSONObject CallBack(String code, String state) {
		String redirectUrl = "", apiURL = "";
		ResponseEntity<JSONObject> response  =null;
		try {
			redirectUrl = URLEncoder.encode(REDIRECT_URL, "UTF-8");
			apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			apiURL += "client_id=" + CLIENT_ID;
			apiURL += "&client_secret=" + SECRET_ID;
			apiURL += "&redirect_uri=" + redirectUrl;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;

			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.exchange(apiURL, HttpMethod.GET, null, JSONObject.class);
			if(response.getStatusCodeValue() != 200) {
				throw new Exception("요청이 완료되지 않음.");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.getBody();
	}

	// body 에 잇는 response 값을 return 
	// 토큰을 보내, 사용자 정보를 얻어옴 
	public JSONObject getCustomInfo(JSONObject json) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",json.get("token_type")+" "+json.get("access_token"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<JSONObject> response = 	restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, entity, JSONObject.class);
		// Object를 Json으로 변환해주기 위해 사용
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(response.getBody().get("response"), JSONObject.class);
	}
	
	
	public NaverUserDTO convertToNaverDTO(String code,String state) {
			JSONObject json = null, loginInfo = null;
			NaverUserDTO dto = null;
			String email = null, nickname = null, profileImage = null, 
					 id = null, name = null;
			json = CallBack(code, state);
			loginInfo = getCustomInfo(json);
			if (loginInfo != null) {
				// 이렇게 해도 되나?
				email =loginInfo.get("email").toString();
				nickname = loginInfo.get("nickname").toString();
				profileImage = loginInfo.get("profile_image").toString();
				id = loginInfo.get("id").toString();
				name = loginInfo.get("name").toString();
				
				dto = new NaverUserDTO(email, nickname,profileImage, name,Integer.parseInt(id));
				dto.setCreateDate(new Timestamp(System.currentTimeMillis()));
				dto.setModifyDate(new Timestamp(System.currentTimeMillis()));
			} else {
				log.debug("json 못받아옴 ");
			}
			return dto;
		
	}

}
