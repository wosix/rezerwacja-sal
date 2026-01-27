package org.example.repository;

import org.example.model.Boardroom;
import org.example.model.Reservation;
import org.example.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ReservationRepository implements IRepository<Reservation, Long> {

    private static ReservationRepository instance;
    private static BoardroomRepository boardroomRepository;

    private final Map<Long, Reservation> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private ReservationRepository() {
        this.boardroomRepository = BoardroomRepository.getInstance();
        Optional<Boardroom> boardroom = boardroomRepository.findById(2L);

        save(new Reservation(1L, boardroom.get(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), ReservationStatus.CONFIRMED));
    }

    public static synchronized ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepository();
        }
        return instance;
    }

    @Override
    public void save(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation.setId(idGenerator.getAndIncrement());
        }
        database.put(reservation.getId(), reservation);
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public long count() {
        return database.size();
    }

    public List<Reservation> findAllByUserId(Long id) {
        return database.values().stream()
                .filter(reservation -> id.equals(reservation.getUserId()))
                .toList();
    }

    public List<Reservation> findByBoardroomId(Long boardroomId) {
        return database.values().stream()
                .filter(reservation -> reservation.getBoardroom() != null &&
                        reservation.getBoardroom().getId().equals(boardroomId))
                .collect(Collectors.toList());
    }

}
