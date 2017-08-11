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
    public Long findProductById(Long productId) {
        return productDao.findProudctId(productId);
    }

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
    public List<ProductDto> getProductListByCategoryId(Long categoryId, Long offsetId) {
        if(categoryId == 0){
            return productDao.selectProductList();
        } else if (categoryService.findCategoryById(categoryId) != null) {
            return productDao.selectProductListByCategoryId(categoryId, offsetId);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetailsDto getProductDetailsByProductId(Long productId) {
        if (findProductById(productId) != null) {
            ProductDetailsDto productDetailsDto = productDao.selectProductDetailsByProductId(productId);
            productDetailsDto.setFileIdList(fileDao.selectFileIdsByProductId(productId));
            return productDetailsDto;
        }
        return null;
    }
}
