package com.gavas.service;

import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Long findProductById(Long productId);
    Long getProductCountByCategoryId(Long id);
    List<ProductDto> getProductListByCategoryId(Long id);
    ProductDetailsDto getProductDetailsByProductId(Long productId);
}
