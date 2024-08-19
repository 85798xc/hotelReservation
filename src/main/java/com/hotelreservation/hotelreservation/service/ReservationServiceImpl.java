package com.hotelreservation.hotelreservation.service;


import com.hotelreservation.hotelreservation.entity.Reservation;
import com.hotelreservation.hotelreservation.persistence.mapper.ReservationDto;
import com.hotelreservation.hotelreservation.persistence.mapper.ReservationMapper;
import com.hotelreservation.hotelreservation.persistence.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.query(reservation -> true); // Use an appropriate specification
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        reservationRepository.createReservation(reservation);
        return reservationMapper.toDto(reservation);
    }

    public ReservationDto getReservationById(Integer id) {
        Optional<Reservation> optionalReservation = reservationRepository.query(reservation -> reservation.getId().equals(id)).stream().findFirst();
        Reservation reservation = optionalReservation.orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        return reservationMapper.toDto(reservation);
    }

    public ReservationDto updateReservation(Integer id, ReservationDto reservationDto) {
        Reservation reservationUpdateTo = reservationMapper.toEntity(reservationDto);
        Optional<Reservation> optionalReservation = reservationRepository.updateReservation(id, reservationUpdateTo);
        Reservation reservation = optionalReservation.orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        return reservationMapper.toDto(reservation);
    }

    public void deleteReservation(Integer id) {
        List<Reservation> reservations = reservationRepository.query(reservation -> reservation.getId().equals(id));
        if (reservations.isEmpty()) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
        reservationRepository.deleteReservation(reservations.get(0));
    }
}
