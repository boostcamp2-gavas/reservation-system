package com.gavas.controller.rest;

import com.gavas.domain.Category;
import com.gavas.domain.dto.ProductDto;
import com.gavas.service.CategoryService;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
