package com.hotelreservation.hotelreservation.persistence.mapper;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Integer id;
    private String clientFullName;
    private Integer roomNumber;
    private List<LocalDate> reservationDates;
}
