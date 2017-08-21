package com.gavas.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {
    private Long id;
    private Long productId;
    private Long userId;
    private Long generalTicketCount;
    private Long youthTicketCount;
    private Long childTicketCount;
    private Long reservationType;
    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private String observationTime;
    private Date displayStart;
    private Date displayEnd;
    private String placeName;
    private String name;
    private Long totalPrice;
}