package com.gavas.controller.rest;

import com.gavas.domain.Category;
import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.exception.EmptyQueryResultException;
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
//    @Autowired
    private CategoryService categoryService;
//    @Autowired
    private ProductService productService;

    @Autowired
    public CategoryRestController(CategoryService categoryService, ProductService productService){
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList() {

        return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/productscount")
    public ResponseEntity<Long> getCategoryCount(@PathVariable Long categoryId) {
        return new ResponseEntity<>(productService.getProductCountByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductListByCategoryId(@PathVariable Long categoryId, @RequestParam("offsetId") Long offsetId) {
        return new ResponseEntity<>(productService.getProductListByCategoryId(categoryId,offsetId), HttpStatus.OK);
    }

    @ExceptionHandler(EmptyQueryResultException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCategoryIdException(EmptyQueryResultException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
