package com.gavas.service;

import com.gavas.domain.dto.TotalCommentStatusDto;

public interface UserCommentService {
    TotalCommentStatusDto getTotalCommentStatus(Long productId);
}
