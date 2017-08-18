package com.gavas.service;

import com.gavas.domain.Reservation;
import com.gavas.domain.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    Integer addReservation(Reservation reservation);
    Integer updateReservationType(Reservation reservation);
    List<ReservationDto> getReservationList(Long id);
}
