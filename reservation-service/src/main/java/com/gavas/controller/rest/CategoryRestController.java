package com.gavas.controller.rest;

import com.gavas.domain.Category;
import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.exception.InvalidCategoryIdException;
import com.gavas.service.CategoryService;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
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

    @ExceptionHandler(InvalidCategoryIdException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCategoryIdException(InvalidCategoryIdException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("test", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
