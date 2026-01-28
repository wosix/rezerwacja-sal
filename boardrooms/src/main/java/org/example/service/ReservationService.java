package org.example.service;

import org.example.model.Boardroom;
import org.example.model.Reservation;
import org.example.model.dto.ReservationTableDTO;
import org.example.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> getByUserId(Long userId);

    List<Reservation> getByDate(LocalDateTime localDateTime);

    Optional<Reservation> getById(Long id);

    void delete(Long id);

    void update(Long id, Reservation reservation);

    void makeReservation(Long userId, Boardroom boardroomId, LocalDateTime dateTime);

    void cancel(Long id);

    void updateStatus(Long id, ReservationStatus status);

    boolean isHourBooked(Long boardroomId, LocalDateTime date, int hour);

    ReservationTableDTO mapToTableDto(Reservation reservation);

}
