package com.gavas.service;

import com.gavas.domain.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    List<ProductPrice> getProductPriceList(Long id);
}
