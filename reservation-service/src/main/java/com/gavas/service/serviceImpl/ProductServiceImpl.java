package com.gavas.service.serviceImpl;

import com.gavas.dao.FileDao;
import com.gavas.dao.ProductDao;
import com.gavas.domain.dto.ProductDetailsDto;
import com.gavas.domain.dto.ProductDto;
import com.gavas.domain.dto.ProductReserveDto;
import com.gavas.service.CategoryService;
import com.gavas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private CategoryService categoryService;
    private ProductDao productDao;
    private FileDao fileDao;

    @Autowired
    public ProductServiceImpl(CategoryService categoryService, ProductDao productDao, FileDao fileDao) {
        this.categoryService = categoryService;
        this.productDao = productDao;
        this.fileDao = fileDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getProductById(Long productId) {
        return productDao.selectProuductId(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getProductCountByCategoryId(Long categoryId) {
        if(categoryId == 0){
            return productDao.selectProductCount();
        } else if (categoryService.findCategoryById(categoryId) != null) {
            return productDao.selectProductCountByCategoryId(categoryId);
        }
        return 0L;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> getProductListByCategoryId(Long categoryId, Long offsetId) {
        if(categoryId == 0){
            return productDao.selectProductList(offsetId);
        } else if (categoryService.findCategoryById(categoryId) != null) {
            return productDao.selectProductListByCategoryId(categoryId, offsetId);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetailsDto getProductDetailsByProductId(Long productId) {
        if (getProductById(productId) != null) {
            ProductDetailsDto productDetailsDto = productDao.selectProductDetailsByProductId(productId);
            productDetailsDto.setFileIdList(fileDao.selectFileIdsByProductId(productId));
            return productDetailsDto;
        }
        return null;
    }
    
    @Transactional(readOnly = true)
    @Override
    public String getProductNameByProductId(Long productId) {
        return productDao.selectProductNameByProductId(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductReserveDto getProductReserveInfoByProductId(Long productId) {
        if (getProductById(productId) != null){
                ProductReserveDto productReserveDto = productDao.selectProductReserveInfoByProductId(productId);
                productReserveDto.setProductPriceInfoDtoList(productDao.selectProductPriceInfoByProductId(productId));
                return productReserveDto;
            }
        return null;
    }
}
