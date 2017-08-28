package com.gavas.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ModelAndView getCategoryIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("인증정보");
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        log.info(authentication.getAuthorities().toString());
        return new ModelAndView("categoryadmin");
    }
}
