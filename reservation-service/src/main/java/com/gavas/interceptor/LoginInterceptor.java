package com.gavas.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("USER") != null) {
            log.info("user login");
            return true;
        } else {
            log.info("redirect for login");
            String url = request.getRequestURI() + "?" + request.getQueryString();
            httpSession.setAttribute("URL", url);
            response.sendRedirect("/login");
            return false;
        }
    }
}
