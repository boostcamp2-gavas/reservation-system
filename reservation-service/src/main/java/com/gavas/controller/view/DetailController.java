package com.gavas.controller.view;

import com.gavas.domain.dto.ProductDto;
import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.service.ProductService;
import com.gavas.service.UserCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/details")
public class DetailController {

    private UserCommentService userCommentService;
    private ProductService productService;

    @Autowired
    public DetailController(UserCommentService userCommentService, ProductService productService){
        this.userCommentService = userCommentService;
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ModelAndView detailPage(@PathVariable Long productId) {
        ModelAndView mv = new ModelAndView("detail");
        TotalCommentStatusDto totalCommentStatusDto = userCommentService.getTotalCommentStatus(productId);
        mv.addObject(productId);
        mv.addObject("count",totalCommentStatusDto.getCount());
        mv.addObject("avg",totalCommentStatusDto.getAvg().setScale(2,2));
        mv.addObject("star", totalCommentStatusDto.getAvg().setScale(2,2).multiply(new BigDecimal(20)));
        return mv;
    }

    @GetMapping("/{productId}/comments")
    public ModelAndView userCommentPage(@PathVariable Long productId) {
        ModelAndView mv = new ModelAndView("review");
        String productName = productService.getProductNameByProductId(productId);
        TotalCommentStatusDto totalCommentStatusDto = userCommentService.getTotalCommentStatus(productId);
        mv.addObject("productName", productName);
        mv.addObject("productId", productId);
        mv.addObject("count",totalCommentStatusDto.getCount());
        mv.addObject("avg",totalCommentStatusDto.getAvg().setScale(2,2));
        mv.addObject("star", totalCommentStatusDto.getAvg().setScale(2,2).multiply(new BigDecimal(20)));
        log.info("called reviews pid : " + productId + " name : " + productName);
        return mv;
    }
}
