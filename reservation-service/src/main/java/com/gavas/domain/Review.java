package com.gavas.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    Integer id;
    Integer productId;
    Integer userId;
    BigDecimal score;
    String comment;
    Date createDate;
    Date modifyDate;
}
