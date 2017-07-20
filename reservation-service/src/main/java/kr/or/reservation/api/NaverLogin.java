package kr.or.reservation.api;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

public class NaverLogin {
	final static String CLIENT_ID = "w0YSpFZqo6SXUXy5itSy";
	final static String REDIRECT_URL = "http://10.81.25.154/callback";
	final static String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id="
			+ "&redirect_uri=" + "&state=";
	final static String SECRET_ID = "IxSbeRZI3A";
	
	public static String getLoginURL(HttpSession session) {
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
}
