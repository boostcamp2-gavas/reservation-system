package com.gavas.controller.rest;


import com.gavas.domain.dto.*;
import com.gavas.exception.EmptyQueryResultException;
import com.gavas.service.ProductService;
import com.gavas.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserCommentService userCommentService;

    @GetMapping("/{productId}/details")
    public ResponseEntity<ProductDetailsDto> getDetailsByProductId(@PathVariable Long productId) {
        ProductDetailsDto productDetailsByProductId = productService.getProductDetailsByProductId(productId);
        return new ResponseEntity<>(productDetailsByProductId, HttpStatus.OK);
    }

    @GetMapping("/{productId}/commentsstatus")
    public ResponseEntity<TotalCommentStatusDto> getTotalCommentStatus(@PathVariable Long productId) {
        TotalCommentStatusDto totalCommentStatusDto = userCommentService.getTotalCommentStatus(productId);
        return new ResponseEntity<>(totalCommentStatusDto, HttpStatus.OK);
    }

    @GetMapping("/{productId}/usercommnets")
    public ResponseEntity<List<UserCommentDto>> getUserCommnetList(@PathVariable Long productId, @RequestParam("commentid") Long commentId, @RequestParam("limit") Integer limit) {
        List<UserCommentDto> userCommentDtoList = userCommentService.getUserCommentDtoByProductId(productId,commentId,limit);
        return new ResponseEntity<>(userCommentDtoList,HttpStatus.OK);
    }

    @GetMapping("/{productId}/reservations")
    public List<ProductPriceInfoDto> getProductPriceInfo(@PathVariable Long productId) {
        return null;
    }

    @ExceptionHandler(EmptyQueryResultException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidException(EmptyQueryResultException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
