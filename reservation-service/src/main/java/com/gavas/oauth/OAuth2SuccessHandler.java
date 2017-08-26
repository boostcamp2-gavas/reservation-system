package com.gavas.oauth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavas.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.gavas.domain.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler
{
    private UserService userService;
    final String type;

    public OAuth2SuccessHandler(String type, UserService userService)
    {
        this.userService = userService;
        this.type = type;
    }

    protected Collection<GrantedAuthority> generateAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
            throws IOException, ServletException
    {
        Map<String, Object> userInfo = (HashMap<String, Object>) auth.getPrincipal();
        System.out.println(userInfo);
        System.out.println("abcde");
        Map<String, Object> jsonUserInfo = (HashMap<String, Object>)userInfo.get("response");
        String id = (String) jsonUserInfo.get("enc_id");

        User user = userService.getUser(id);

        if ( user == null) {
            jsonUserInfo.put("adminFlag",1);
            jsonUserInfo.put("snsId", id);
            jsonUserInfo.put("username", (String) jsonUserInfo.get("name"));
            ModelMapper modelMapper = new ModelMapper();
            user = modelMapper.map(jsonUserInfo, User.class);
            userService.addUser(user);
        }

        if (user.getAdminFlag() == 0) {
            SecurityContextHolder.getContext().setAuthentication(new AuthenticationToken(user, null, generateAuthorities()));
        }

        res.sendRedirect("/");
    }
}