package com.gavas.controller.view;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reviewWrite")
public class ReviewController {
    private ProductService productService;

    @Autowired
    public ReviewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ModelAndView reviewWritePage(@RequestParam("productId") Long productId, @AuthUser User user) {
        ModelAndView mv = new ModelAndView("reviewwrite");
        String productName = productService.getProductNameByProductId(productId);
        mv.addObject("productId", productId);
        mv.addObject("userId", user.getId());
        mv.addObject("productName",productName);
        return mv;
    }
}
