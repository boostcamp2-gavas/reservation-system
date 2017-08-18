package com.gavas.controller.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @GetMapping("/{productId}")
    public ModelAndView reservePage(@PathVariable Long productId){
        ModelAndView mv = new ModelAndView("reserve");
        mv.addObject(productId);
        return mv;
    }
}
