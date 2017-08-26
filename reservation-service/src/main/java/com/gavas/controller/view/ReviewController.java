package com.gavas.controller.view;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.User;
import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.exception.EmptyQueryResultException;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reviewWrite")
public class ReviewController {
    ProductService productService;

    @Autowired
    public ReviewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView reviewWritePage(@RequestParam("productId") Long productId, @AuthUser User user) {
        ModelAndView mv = new ModelAndView("reviewwrite");
        String productName = productService.selectProductNameByProductId(productId);
        mv.addObject("productId", productId);
        mv.addObject("userId", user.getId());
        mv.addObject("productName",productName);
        return mv;
    }
}
