package org.example.service;

import org.example.model.Reservation;
import org.example.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> getByUserId(int userId);

    List<Reservation> getByDate(LocalDateTime localDateTime);

    Reservation getById(int id);

    void delete(int id);

    Reservation update(int id, Reservation boardroom);

    Reservation updateStatus(int id, ReservationStatus status);

}
