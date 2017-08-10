package com.gavas.service.serviceImpl;

import com.gavas.dao.UserCommentDao;
import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.service.ProductService;
import com.gavas.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserCommentDao userCommentDao;

    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    @Override
    public TotalCommentStatusDto getTotalCommentStatus(Long productId) {
        if (productService.findProductById(productId) != null) {
            return userCommentDao.selectTotalCommentStatusByProductId(productId);
        }
        return null;
    }
}
