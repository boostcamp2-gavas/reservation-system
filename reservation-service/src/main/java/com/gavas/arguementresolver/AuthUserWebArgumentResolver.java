package com.gavas.arguementresolver;

import com.gavas.domain.User;
import com.gavas.exception.EmptyQueryResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class AuthUserWebArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthUser loginUser = parameter.getParameterAnnotation(AuthUser.class);
        if (loginUser == null)
            return false;
        else
            return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            log.info(authentication.getPrincipal().toString());
            log.info(authentication.getAuthorities().toString());
            User naverLoginUser = (User) authentication.getPrincipal();
            return naverLoginUser;
        } catch (NullPointerException exception) {
            return null;
        }
    }
}
