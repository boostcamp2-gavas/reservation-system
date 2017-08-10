package com.gavas.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TotalCommentStatusDto {
    private Long productId;
    private Long count;
    private BigDecimal avg;
}
