package com.gavas.controller.rest;


import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductPriceInfoDto;
import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.domain.dto.UserCommentDto;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}/details")
    public ResponseEntity<ProductDetailsDto> getDetailsByProductId(@PathVariable Long productId) {
        ProductDetailsDto productDetailsByProductId = productService.getProductDetailsByProductId(productId);
        return new ResponseEntity<>(productDetailsByProductId, HttpStatus.OK);
    }



//    @GetMapping("/{productId}/details")
//    public ProductDetailsDto getDetailsByProductId(@PathVariable Long productId) {
//        return productService.getProductDetailsByProductId(productId);
//    }

    @GetMapping("/{productId}/commentsstatus")
    public TotalCommentStatusDto getTotalCommentStatus(@PathVariable Long productId) {

        return null;
    }

    @GetMapping("/{productId}/usercommnets")
    public List<UserCommentDto> getUserCommnetList(@PathVariable Long productId) {
        return null;
    }

    @GetMapping("/{productId}/reservations")
    public List<ProductPriceInfoDto> getProductPriceInfo(@PathVariable Long productId) {
        return null;
    }



}
