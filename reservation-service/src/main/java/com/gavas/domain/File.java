package com.gavas.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {
    private Long id;
    private Long userId;
    private String fileName;
    private String saveFileName;
    private Long fileLength;
    private String contentType;
    private Integer deleteFlag;
    private Date createDate;
    private Date modifyDate;

}
