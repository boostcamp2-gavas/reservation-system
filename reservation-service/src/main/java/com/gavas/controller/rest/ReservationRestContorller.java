package com.gavas.controller.rest;

import com.gavas.arguementresolver.AuthUser;
import com.gavas.domain.Reservation;
import com.gavas.domain.User;
import com.gavas.domain.dto.ErrorResponseDto;
import com.gavas.domain.dto.ReservationDto;
import com.gavas.exception.DataAccessFailException;
import com.gavas.exception.EmptyQueryResultException;
import com.gavas.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestContorller {
    private ReservationService reservationService;

    @Autowired
    public ReservationRestContorller(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservationDtoByUserId(@AuthUser User user) {
        List<ReservationDto> reservationDtoList = reservationService.getReservationList(user.getId());
        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateReservationType(@PathVariable Long id, @RequestBody Reservation reservation) {
        Integer updatesCount = reservationService.updateReservationType(reservation);
        return new ResponseEntity<>(updatesCount, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addReservation(@RequestBody Reservation reservation) {
        Integer reservationId = reservationService.addReservation(reservation);
        return new ResponseEntity<>(reservationId, HttpStatus.OK);
    }

    @ExceptionHandler(EmptyQueryResultException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidException(EmptyQueryResultException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessFailException.class)
    public ResponseEntity<ErrorResponseDto> handleFailException(DataAccessFailException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("409", exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }


}
