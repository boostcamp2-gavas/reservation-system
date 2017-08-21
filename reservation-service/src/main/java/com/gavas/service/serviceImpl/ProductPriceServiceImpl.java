package com.gavas.service.serviceImpl;

import com.gavas.dao.ProductPriceDao;
import com.gavas.domain.ProductPrice;
import com.gavas.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private ProductPriceDao productPriceDao;

    @Autowired
    public ProductPriceServiceImpl(ProductPriceDao productPriceDao) {
        this.productPriceDao = productPriceDao;
    }

    @Override
    public List<ProductPrice> getProductPriceList(Long id) {
        return productPriceDao.selectProductPriceByProductId(id);
    }
}
