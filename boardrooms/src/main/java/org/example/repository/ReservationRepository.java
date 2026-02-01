package org.example.repository;

import org.example.model.entity.Reservation;
import org.example.model.enums.ReservationStatus;
import org.example.repository.jpa.IRepository;

import java.util.List;

public interface ReservationRepository extends IRepository<Reservation, Long> {

    List<Reservation> findAllByAccountId(Long accountId);

    List<Reservation> findByBoardroomId(Long boardroomId);

    List<Reservation> findByStatus(ReservationStatus status);

}
