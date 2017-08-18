package com.gavas.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("USER") != null) {
            logger.info("user login");
            return true;
        } else {
            logger.info("redirect for login");
            String url = request.getRequestURI() + "?" + request.getQueryString();
            httpSession.setAttribute("URL", url);
            response.sendRedirect("/login");
            return false;
        }
    }
}
