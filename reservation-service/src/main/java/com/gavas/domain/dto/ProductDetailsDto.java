package com.gavas.domain.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetailsDto {
    private Long id;
    private String name;
    private String description;
    private String placeName;
    private Date salesStart;
    private Date salesEnd;
    private Integer salesFlag;
    private String event;
    private List<Long> fileIdList;
}
