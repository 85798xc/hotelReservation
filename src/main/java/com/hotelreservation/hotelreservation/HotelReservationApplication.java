package com.hotelreservation.hotelreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HotelReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationApplication.class, args);
    }

}
