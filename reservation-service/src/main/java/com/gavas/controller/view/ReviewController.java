package com.gavas.controller.view;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reviewWrite")
public class ReviewController {
    @GetMapping
    public ModelAndView reviewWritePage(@RequestParam("productId") Long productId) {
        ModelAndView mv = new ModelAndView("reviewwrite");
        mv.addObject("productId",productId);
        return mv;
    }
}
