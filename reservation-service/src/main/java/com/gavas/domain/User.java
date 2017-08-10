package com.gavas.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String tel;
    private String nickname;
    private String snsId;
    private String snsType;
    private String snsProfile;
    private Long adminFlag;
    private Date createDate;
    private Date modifyDate;
}
