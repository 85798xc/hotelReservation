package com.hotelreservation.hotelreservation.persistence.mapper;


import com.hotelreservation.hotelreservation.entity.Reservation;
import org.springframework.stereotype.Component;



@Component
public class ReservationMapper {

    public ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .clientFullName(reservation.getClientFullName())
                .roomNumber(reservation.getRoomNumber())
                .reservationDates(reservation.getReservationDates())
                .build();
    }

    public Reservation toEntity(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .clientFullName(reservationDto.getClientFullName())
                .roomNumber(reservationDto.getRoomNumber())
                .reservationDates(reservationDto.getReservationDates())
                .build();
    }
}
