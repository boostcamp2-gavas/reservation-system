package com.gavas.service.serviceImpl;

import com.gavas.dao.FileDao;
import com.gavas.dao.ProductDao;
import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.service.CategoryService;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private FileDao fileDao;

    @Transactional(readOnly = true)
    @Override
    public Long getProductCountByCategoryId(Long categoryId) {
        if (categoryService.findCategoryById(categoryId) != null) {
            return productDao.selectProductCountByCategoryId(categoryId);
        }
        return 0L;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> getProductListByCategoryId(Long categoryId) {
        return productDao.selectProductListByCategoryId(categoryId);
    }

    @Override
    public ProductDetailsDto getProductDetailsByProductId(Long productId) {
        ProductDetailsDto productDetailsDto = productDao.selectProductDetailsByProductId(productId);
        productDetailsDto.setFileIdList(fileDao.selectFileIdsByProductId(productId));
        return productDetailsDto;
    }
}
