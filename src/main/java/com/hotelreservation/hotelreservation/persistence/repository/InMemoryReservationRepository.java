package com.hotelreservation.hotelreservation.persistence.repository;

import com.hotelreservation.hotelreservation.entity.Reservation;
import com.hotelreservation.hotelreservation.persistence.specification.ReservationSpecification;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryReservationRepository implements ReservationRepository {

    private Set<Reservation> reservations = new HashSet<>();
    private static final String DATA_FILE_PATH = "reservations.ser";

    public InMemoryReservationRepository() {
        loadReservationsFromFile();
    }

    @Override
    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
        saveReservationsToFile();
    }

    @Override
    public Optional<Reservation> updateReservation(Integer id, Reservation reservation) {

        boolean removed = reservations.removeIf(r -> r.getId().equals(id));
        if (removed) {
            reservations.add(reservation);
            saveReservationsToFile();
            return Optional.of(reservation);
        }
        return Optional.empty();
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
        saveReservationsToFile();
    }

    @Override
    public List<Reservation> query(ReservationSpecification specification) {
        return reservations.stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }

    private void loadReservationsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
            reservations = (Set<Reservation>) ois.readObject();
        } catch (FileNotFoundException e) {
            reservations = new HashSet<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveReservationsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
