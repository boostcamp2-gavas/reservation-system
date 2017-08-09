package com.gavas.controller.view;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/details")
public class DetailController {

    @GetMapping("/{productId}")
    public ModelAndView detailPage(@PathVariable Long productId) {
        ModelAndView mv = new ModelAndView("detail");
        mv.addObject(productId);
        return mv;
    }
}
