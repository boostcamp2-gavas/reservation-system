package kr.or.reservation.api;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import kr.or.common.StringToJsonParser;

public class NaverLogin {
	final static String CLIENT_ID = "w0YSpFZqo6SXUXy5itSy";
	final static String REDIRECT_URL = "http://10.81.25.154/callback";
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
			redirectUrl = URLEncoder.encode("REDIRECT_URL", "UTF-8");
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
		int responseCode = 0;
		String redirectUrl = "", apiURL = "", inputLine = null;
		BufferedReader br = null;
		HttpURLConnection con = null;
		URL url = null;
		JSONObject jsonObj = null;
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

				jsonObj = StringToJsonParser.JsonParser(res.toString());
				// 이러면 response 에 토큰을 줌
				log.info("토큰 : " + jsonObj.get("access_token"));

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 자원 반환
				if (con != null) {
					con.disconnect();
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return jsonObj;
	}

	public JSONObject getCustomInfo(JSONObject json) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// Header 등록
		headers.set("Authorization",json.get("token_type")+" "+json.get("access_token"));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = 	restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, entity, String.class);
		// body 정보 가져오기
		JSONObject body = StringToJsonParser.JsonParser(response.getBody().toString());
		// 회원 정보 가져오기 
		JSONObject data  = StringToJsonParser.JsonParser(body.get("response").toString());
		return data;
	}

}
