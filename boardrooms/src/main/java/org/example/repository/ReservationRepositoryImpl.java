package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.entity.Reservation;
import org.example.model.enums.ReservationStatus;
import org.example.repository.jpa.Database;
import org.example.repository.jpa.GenericRepository;

import java.util.List;

public class ReservationRepositoryImpl extends GenericRepository<Reservation, Long> implements ReservationRepository {

    public ReservationRepositoryImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findAllByAccountId(Long accountId) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT r FROM Reservation r " +
                                    "LEFT JOIN r.account a " +
                                    "WHERE a.id = :accountId", Reservation.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
        }
    }

    @Override
    public List<Reservation> findByBoardroomId(Long boardroomId) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT r FROM Reservation r " +
                                    "LEFT JOIN r.boardroom b " +
                                    "WHERE b.id = :boardroomId", Reservation.class)
                    .setParameter("boardroomId", boardroomId)
                    .getResultList();
        }
    }

    @Override
    public List<Reservation> findByStatus(ReservationStatus status) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT r FROM Reservation r " +
                                    "JOIN FETCH r.account a " +
                                    "JOIN FETCH r.boardroom b " +
                                    "WHERE r.status = :status", Reservation.class)
                    .setParameter("status", status)
                    .getResultList();
        }
    }

}
