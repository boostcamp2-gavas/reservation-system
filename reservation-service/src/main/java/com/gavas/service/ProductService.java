package com.gavas.service;

import com.gavas.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    Long getProductCountByCategoryId(Long id);
    List<ProductDto> getProductListByCategoryId(Long id);
}
