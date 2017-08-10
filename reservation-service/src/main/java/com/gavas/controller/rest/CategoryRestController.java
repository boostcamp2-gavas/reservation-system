package com.gavas.controller.rest;

import com.gavas.domain.Category;
import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.exception.EmptyQueryResultException;
import com.gavas.service.CategoryService;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD:reservation-service/src/main/java/com/gavas/controller/CategoryController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
>>>>>>> e3da0ac26a38ffdb4bdb2a3c3dc1ef0e71b3014e:reservation-service/src/main/java/com/gavas/controller/rest/CategoryRestController.java

import java.util.List;

@RestController
@RequestMapping("/api/categories")
<<<<<<< HEAD:reservation-service/src/main/java/com/gavas/controller/CategoryController.java
public class CategoryController {
=======
public class CategoryRestController {
>>>>>>> e3da0ac26a38ffdb4bdb2a3c3dc1ef0e71b3014e:reservation-service/src/main/java/com/gavas/controller/rest/CategoryRestController.java
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Category> getCategoryList(){

        return categoryService.getCategoryList();
    }

    @GetMapping("/{categoryId}/productscount")
    public Long getCategoryCount(@PathVariable Long categoryId){
        return productService.getProductCountByCategoryId(categoryId);
    }

    @GetMapping("/{categoryId}/product")
    public List<ProductDto> getProductListByCategoryId(@PathVariable Long categoryId){

        return productService.getProductListByCategoryId(categoryId);
    }

    @ExceptionHandler(EmptyQueryResultException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCategoryIdException(EmptyQueryResultException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
