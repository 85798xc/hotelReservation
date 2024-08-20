package com.hotelreservation.hotelreservation.persistence.specification;

import com.hotelreservation.hotelreservation.entity.Reservation;

public interface ReservationSpecification {

    boolean specified(Reservation reservation);
}
