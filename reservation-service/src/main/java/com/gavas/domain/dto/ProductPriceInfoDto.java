package com.gavas.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPriceInfoDto {
    private Long priceType;
    private Long price;
    private BigDecimal discountRate;
}
