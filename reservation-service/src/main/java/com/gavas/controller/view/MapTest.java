package com.gavas.controller.view;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class MapTest {
    @GetMapping("map")
    public ModelAndView map() {
        return new ModelAndView("testmap");
    }
}
