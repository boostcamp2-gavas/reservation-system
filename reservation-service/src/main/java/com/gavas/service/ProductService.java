package com.gavas.service;

import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.domain.dto.ProductReserveDto;

import java.util.List;

public interface ProductService {
    Long findProductById(Long productId);
    Long getProductCountByCategoryId(Long id);
    List<ProductDto> getProductListByCategoryId(Long id, Long offsetId);
    ProductDetailsDto getProductDetailsByProductId(Long productId);
    String selectProductNameByProductId(Long productId);
    ProductReserveDto getProductReserveInfoByProductId(Long productId);
}
