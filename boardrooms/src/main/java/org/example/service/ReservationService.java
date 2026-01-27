package org.example.service;

import org.example.model.Reservation;
import org.example.model.ReservationStatus;
import org.example.model.dto.ReservationTableDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> getByUserId(Long userId);

    List<Reservation> getByDate(LocalDateTime localDateTime);

    Optional<Reservation> getById(Long id);

    void delete(Long id);

    void update(Long id, Reservation boardroom);

    void cancel(Long id);
    
    void updateStatus(Long id, ReservationStatus status);

    ReservationTableDTO mapToTableDto(Reservation reservation);

}
