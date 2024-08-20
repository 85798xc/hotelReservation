package com.hotelreservation.hotelreservation.service;

import com.hotelreservation.hotelreservation.entity.Reservation;
import com.hotelreservation.hotelreservation.persistence.mapper.ReservationDto;
import com.hotelreservation.hotelreservation.persistence.mapper.ReservationMapper;
import com.hotelreservation.hotelreservation.persistence.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {
    @Mock
    ReservationMapper reservationMapper;
    @Mock
    ReservationRepository reservationRepository;
    @InjectMocks
    ReservationServiceImpl reservationServiceImpl;
    ReservationDto reservationDto;
    Reservation reservation;

    @BeforeEach
    public void init() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setClientFullName("TEST");

        reservation = new Reservation();
        reservation.setId(1);
        reservation.setClientFullName("TEST");
    }

    @Test
    void getAllReservations() {

        when(reservationRepository.query(any())).thenReturn(Arrays.asList(reservation));
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        List<ReservationDto> reservationDtos = reservationServiceImpl.getAllReservations();

        assertNotNull(reservationDtos);
        assertEquals(1, reservationDtos.size());
        assertEquals("TEST", reservationDtos.get(0).getClientFullName());

        verify(reservationRepository, times(1)).query(any());
        verify(reservationMapper, times(1)).toDto(reservation);
    }

    @Test
    void createReservation() {
        when(reservationMapper.toEntity(reservationDto)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto createdReservation = reservationServiceImpl.createReservation(reservationDto);

        assertNotNull(createdReservation);
        assertEquals("TEST", createdReservation.getClientFullName());

        verify(reservationRepository, times(1)).createReservation(reservation);
        verify(reservationMapper, times(1)).toEntity(reservationDto);
        verify(reservationMapper, times(1)).toDto(reservation);
    }

    @Test
    void getReservationById() {
        when(reservationRepository.query(any())).thenReturn(Arrays.asList(reservation));
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto foundReservation = reservationServiceImpl.getReservationById(1);

        assertNotNull(foundReservation);
        assertEquals(1, foundReservation.getId());

        verify(reservationRepository, times(1)).query(any());
        verify(reservationMapper, times(1)).toDto(reservation);
    }

    @Test
    void updateReservation() {
        when(reservationRepository.updateReservation(anyInt(), any())).thenReturn(Optional.of(reservation));
        when(reservationMapper.toEntity(reservationDto)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto updatedReservation = reservationServiceImpl.updateReservation(1, reservationDto);

        assertNotNull(updatedReservation);
        assertEquals(1, updatedReservation.getId());

        verify(reservationRepository, times(1)).updateReservation(anyInt(), any());
        verify(reservationMapper, times(1)).toEntity(reservationDto);
        verify(reservationMapper, times(1)).toDto(reservation);
    }

    @Test
    void deleteReservation() {
        when(reservationRepository.query(any())).thenReturn(Arrays.asList(reservation));

        reservationServiceImpl.deleteReservation(1);

        verify(reservationRepository, times(1)).deleteReservation(reservation);
    }
}