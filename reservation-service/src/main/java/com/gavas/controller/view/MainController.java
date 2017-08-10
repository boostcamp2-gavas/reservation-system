package com.gavas.controller.view;


import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import com.gavas.service.LoginService;
import com.gavas.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {
    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @Value("${open-api.naver.client-id}")
    private String clientId;
    @Value("${open-api.naver.callback-url}")
    private String callbackUrl;

    @GetMapping("reservations")
    public ModelAndView temp(@AuthUser User user) {
        logger.info(user.toString());
        return new ModelAndView("mainpage");
    }

    @GetMapping
    public ModelAndView mainPage() {
        return new ModelAndView("mainpage");
    }

    @GetMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        String state = loginService.generateState();
        HttpSession session = request.getSession();
        String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri=http";

        session.setAttribute("state", state);

        try {
            String encodeURL = URLEncoder.encode(callbackUrl, "UTF-8");

            return new ModelAndView("redirect:" + naverLoginUrl + encodeURL + "&state=" + state + "&auth_type=reauthenticate");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("callback")
    public ModelAndView loginCallback(HttpServletRequest request) {

        String state = request.getParameter("state");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        String storedState = (String) session.getAttribute("state");

        String url = (String) session.getAttribute("URL");

        if (!state.equals(storedState)) {
            return new ModelAndView("mainpage");
        } else {
            Map<String, Object> accessResult = loginService.getAcessToken(state, code);
            Map<String, String> profile = loginService.getProfile((String) accessResult.get("access_token"));

            if (profile == null) {
                return new ModelAndView("redirect:/login");
            }

            User userProfile = userService.getUser(profile.get("id"));

            if (userProfile == null) {
                userProfile = loginService.getUserDto(profile);
                Long id = userService.addUser(userProfile);
                userProfile.setId(id);
            }
            session.setAttribute("USER", userProfile);

            return new ModelAndView("redirect:" + url);
        }

    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.removeAttribute("USER");

        return new ModelAndView("mainpage");
    }
}
