package com.gavas.service.serviceImpl;

import com.gavas.dao.UserCommentDao;
import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.domain.dto.UserCommentDto;
import com.gavas.service.ProductService;
import com.gavas.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserCommentDao userCommentDao;

    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    @Override
    public Long findUserCommentId(Long userCommentId){
        return userCommentDao.findUserCommentId(userCommentId);
    }


    @Transactional(readOnly = true)
    @Override
    public TotalCommentStatusDto getTotalCommentStatus(Long productId) {
        if (productService.findProductById(productId) != null) {
            return userCommentDao.selectTotalCommentStatusByProductId(productId);
        }
        return null;
    }

    @Override
    public List<UserCommentDto> getUserCommentDtoByProductId(Long productId, Long commentId, Integer limit) {
        if(productService.findProductById(productId) != null){
            List<UserCommentDto> userCommentDtoList = userCommentDao.selectUserCommentByProductId(productId,commentId,limit);
            userCommentDtoList.forEach(dto -> dto.setNickName(blockUsername(dto.getNickName())));
            return userCommentDtoList;
        }

        return null;
    }

    @Override
    public List<Long> getFileIdByUserCommentId(Long userCommentId){
        if(findUserCommentId(userCommentId) != null){
            return userCommentDao.selectFileIdByUserCommentId(userCommentId);
        }

        return null;
    }

    private String blockUsername(String username) {
        StringBuilder stringBuilder = new StringBuilder(username);
        stringBuilder.replace(stringBuilder.length()/2+1, stringBuilder.length(), "***");
        return stringBuilder.toString();
    }
}
