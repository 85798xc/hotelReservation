package com.hotelreservation.hotelreservation.sevice;

import com.hotelreservation.hotelreservation.entity.Reservation;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {

    private static final String FILE_PATH = "reservations.ser";
    private Set<Reservation> reservations = new HashSet<>();
    private int currentId = 1;

    public ReservationService() {
        loadReservations();
    }

    public Set<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setId(currentId++);
        reservations.add(reservation);
        saveReservations();
        return reservation;
    }

    public Optional<Reservation> getReservationById(Integer id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst();
    }

    public Optional<Reservation> updateReservation(Integer id, Reservation updatedReservation) {
        return getReservationById(id).map(existingReservation -> {
            existingReservation.setClientFullName(updatedReservation.getClientFullName());
            existingReservation.setRoomNumber(updatedReservation.getRoomNumber());
            existingReservation.setReservationDates(updatedReservation.getReservationDates());
            saveReservations();
            return existingReservation;
        });
    }

    public boolean deleteReservation(Integer id) {
        boolean removed = reservations.removeIf(reservation -> reservation.getId().equals(id));
        if (removed) {
            saveReservations();
        }
        return removed;
    }

    private void saveReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadReservations() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reservations = (Set<Reservation>) ois.readObject();
                currentId = reservations.stream()
                        .mapToInt(Reservation::getId)
                        .max()
                        .orElse(0) + 1;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}