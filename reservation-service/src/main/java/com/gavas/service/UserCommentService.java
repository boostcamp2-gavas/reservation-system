package com.gavas.service;

import com.gavas.domain.dto.TotalCommentStatusDto;
import com.gavas.domain.dto.UserCommentDto;

import java.util.List;

public interface UserCommentService {
    Long findUserCommentId(Long userCommentId);
    TotalCommentStatusDto getTotalCommentStatus(Long productId);
    List<UserCommentDto> getUserCommentDtoByProductId(Long productId, Long commentId, Integer limit);
    List<Long> getFileIdByUserCommentId(Long userCommentId);
}
