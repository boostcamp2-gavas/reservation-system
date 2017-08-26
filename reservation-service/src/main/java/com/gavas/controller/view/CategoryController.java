package com.gavas.controller.view;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class CategoryController {

    @GetMapping
    @Secured("ROLE_USER")
    public ModelAndView getCategoryIndex() {
        return new ModelAndView("index");
    }
}
