package com.gavas.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private String placeName;
    private Long fileId;
}
