package com.gavas.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPrice {
    private Long id;
    private Long productId;
    private Long priceType;
    private Long price;
    private BigDecimal discountRate;
}
