package org.example.service;

import org.example.model.dto.ReservationTableDTO;
import org.example.model.entity.Boardroom;
import org.example.model.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> getByUserId(Long userId);

    Reservation getById(Long id);

    void delete(Long id);

    void update(Long id, Reservation reservation);

    void createReservation(Long userId, Boardroom boardroomId, LocalDateTime dateTime);

    void cancel(Long id);

    boolean isHourBooked(Long boardroomId, LocalDateTime date, int hour);

    ReservationTableDTO mapToTableDto(Reservation reservation);

}
