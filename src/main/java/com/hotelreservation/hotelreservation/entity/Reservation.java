package com.hotelreservation.hotelreservation.entity;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String clientFullName;
    private Integer roomNumber;
    private List<LocalDate> reservationDates;
}