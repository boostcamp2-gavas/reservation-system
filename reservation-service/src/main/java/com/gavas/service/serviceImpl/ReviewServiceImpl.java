package com.gavas.service.serviceImpl;

import com.gavas.dao.ReviewDao;
import com.gavas.domain.Review;
import com.gavas.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Transactional
    @Override
    public Integer addReviewWithFiles(Review review, List<Integer> fileList) {
        return reviewDao.insertWithFiles(review,fileList);
    }

    @Transactional
    @Override
    public Integer addReview(Review review) {
        return reviewDao.insert(review);
    }
}
