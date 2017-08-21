package com.gavas.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {
    private Long id;
    private Long productId;
    private Long userId;
    private Long generalTicketCount;
    private Long youthTicketCount;
    private Long childTicketCount;
    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private Date reservationDate;
    private Long reservationType;
    private Date createDate;
    private Date modifyDate;

}
