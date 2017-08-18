package com.gavas.domain.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductReserveDto {
    private Long id;
    private String name;
    private String description;
    private Date salesStart;
    private Date salesEnd;
    private String observationTime;
    private Date displayStart;
    private Date displayEnd;
    private String placeName;
    private Long fileId;
    private List<ProductPriceInfoDto> productPriceInfoDtoList;
}
