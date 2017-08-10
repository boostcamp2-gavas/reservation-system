package com.gavas.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserComment {
    private Long id;
    private Long productId;
    private Long userId;
    private BigDecimal score;
    private String comment;
    private Date createDate;
    private Date modifyDate;
}
