package com.hotelreservation.hotelreservation.persistence.repository;


import com.hotelreservation.hotelreservation.entity.Reservation;
import com.hotelreservation.hotelreservation.persistence.specification.ReservationSpecification;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository  {


    void createReservation(Reservation reservation);
    Optional<Reservation> updateReservation(Integer id,Reservation reservation);

    void deleteReservation(Reservation reservation);

    List<Reservation> query(ReservationSpecification specification);

}
