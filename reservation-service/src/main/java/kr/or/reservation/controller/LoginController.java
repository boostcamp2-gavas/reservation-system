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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kr.or.common.StringToJsonParser;
import kr.or.reservation.api.NaverLogin;
import kr.or.reservation.dto.NaverUserDTO;

@Controller
public class LoginController {
	// 로그 선언
	Logger log = Logger.getLogger(this.getClass());

	@GetMapping(path = "/callback")
	public String enrollSession(Model model, HttpSession session, @RequestParam String code,
			@RequestParam String state) {
		NaverLogin login = new NaverLogin();
		JSONObject json = null, loginInfo = null;
		NaverUserDTO dto = null;
		String email = null, nickname = null, profileImage = null, age = null, 
				gender = null, id = null, name = null, birthday = null;
		if (state.equals(session.getAttribute("state"))) {
			json = login.CallBack(code, state);
			loginInfo = login.getCustomInfo(json);
			if (loginInfo != null) {
				// 이렇게 해도 되나?
				email =loginInfo.get("email").toString();
				nickname = loginInfo.get("nickname").toString();
				profileImage = loginInfo.get("profile_image").toString();
				age =loginInfo.get("age").toString();
				gender = loginInfo.get("gender").toString();
				id = loginInfo.get("id").toString();
				name = loginInfo.get("name").toString();
				birthday = loginInfo.get("birthday").toString();
				dto = new NaverUserDTO(email, nickname,profileImage, age, gender,
						id, name, birthday);
				//일단 3개만 저장
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("email", email);
			
			} else {
				log.debug("json 못받아옴 ");
			}
		} else {

		}
		return "redirect:/";
	}

}