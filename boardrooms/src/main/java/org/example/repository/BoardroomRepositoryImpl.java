package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.model.entity.Boardroom;
import org.example.model.enums.RoomSize;
import org.example.model.enums.RoomType;
import org.example.repository.jpa.Database;
import org.example.repository.jpa.GenericRepository;

import java.util.List;

public class BoardroomRepositoryImpl extends GenericRepository<Boardroom, Long> implements BoardroomRepository {

    public BoardroomRepositoryImpl() {
        super(Boardroom.class);
    }

    @Override
    public List<Boardroom> findByNumberLike(String number) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.number LIKE :number", Boardroom.class)
                    .setParameter("number", number)
                    .getResultList();
        }
    }

    @Override
    public List<Boardroom> findByNumber(String number) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.number = :number", Boardroom.class)
                    .setParameter("number", number)
                    .getResultList();
        }
    }

    @Override
    public List<Boardroom> findByType(RoomType type) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.roomType = :type", Boardroom.class)
                    .setParameter("type", type)
                    .getResultList();
        }
    }

    @Override
    public List<Boardroom> findBySize(RoomSize size) {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.roomSize = :size", Boardroom.class)
                    .setParameter("size", size)
                    .getResultList();
        }
    }

    @Override
    public List<Boardroom> findByProjector() {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.equipment.hasProjector = true", Boardroom.class)
                    .getResultList();
        }
    }

    @Override
    public List<Boardroom> findByAirConditioning() {
        try (EntityManager em = Database.getEntityManager()) {
            return em.createQuery(
                            "SELECT b FROM Boardroom b WHERE b.equipment.hasAirConditioning = true", Boardroom.class)
                    .getResultList();
        }
    }

}
