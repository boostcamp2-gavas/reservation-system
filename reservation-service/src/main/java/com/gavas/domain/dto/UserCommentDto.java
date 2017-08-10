package com.gavas.domain.dto;

import com.gavas.domain.UserComment;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCommentDto {
    private Long id;
    private Long productId;
    private Long userId;
    private BigDecimal score;
    private String comment;
    private Date createDate;
    private Date modifyDate;
    private String nickName;
    private Long fileId;
    private Long fileCount;
}
