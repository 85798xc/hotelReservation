package com.hotelreservation.hotelreservation.persistence.specification;

import com.hotelreservation.hotelreservation.entity.Reservation;

public class ReservationClientFullNameSpecification  implements ReservationSpecification{

    private final String clientFullName;

    public ReservationClientFullNameSpecification(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    @Override
    public boolean specified(Reservation reservation) {
        return reservation.getClientFullName().equalsIgnoreCase(clientFullName);
    }


}
