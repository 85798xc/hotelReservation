package com.hotelreservation.hotelreservation.service;

import com.hotelreservation.hotelreservation.persistence.mapper.ReservationDto;

import java.util.List;

public interface ReservationService {

   List<ReservationDto> getAllReservations();
   ReservationDto createReservation(ReservationDto reservationDto);
   ReservationDto getReservationById(Integer id);
   ReservationDto updateReservation(Integer id, ReservationDto reservationDto);
   void deleteReservation(Integer id);
}
