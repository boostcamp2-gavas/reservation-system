package com.gavas.service;

import com.gavas.domain.Review;

import java.util.List;

public interface ReviewService {
    Integer addReviewWithFiles(Review review, List<Integer> fileList);
    Integer addReview(Review review);
}
