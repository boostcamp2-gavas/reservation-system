package com.gavas.oauth;

import com.gavas.service.CategoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler
{
    final String type;
//    final CategoryService accountService;

    public OAuth2SuccessHandler(String type)

    {
//     , CategoryService accountService
        this.type = type;
//        this.accountService = accountService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
            throws IOException, ServletException
    {
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());
        res.sendRedirect("/");
    }
}