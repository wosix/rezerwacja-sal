package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.Boardroom;
import org.example.model.Reservation;
import org.example.model.dto.ReservationTableDTO;
import org.example.model.enums.ReservationStatus;
import org.example.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl() {
        this.reservationRepository = ReservationRepository.getInstance();
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId);
    }

    @Override
    public List<Reservation> getByDate(LocalDateTime localDateTime) {
//        return reservationRepository.find;
        return List.of();
    }

    @Override
    public Optional<Reservation> getById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public void update(Long id, Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void makeReservation(Long userId, Boardroom boardroom, LocalDateTime dateTimeStart) {
        LocalDateTime dateTimeEnd = dateTimeStart.plusMinutes(59);
        Reservation reservation = new Reservation(
                userId,
                boardroom,
                dateTimeStart,
                dateTimeEnd,
                ReservationStatus.CONFIRMED
        );
        reservationRepository.save(reservation);
    }

    @Override
    public void cancel(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new NotFoundException("Nie znaleziono rezerwacji o id: " + id);
        }
        Reservation reservation = reservationOptional.get();
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    @Override
    public void updateStatus(Long id, ReservationStatus status) {
//        reservationRepository.save();
    }

    @Override
    public boolean isHourBooked(Long boardroomId, LocalDateTime date, int hour) {
        LocalDateTime hourToCheck = date.withHour(hour).withMinute(0);

        return reservationRepository.findByBoardroomId(boardroomId).stream()
                .anyMatch(reservation ->
                        reservation.getStatus() != ReservationStatus.CANCELLED &&
                                !hourToCheck.isBefore(reservation.getStart()) &&
                                hourToCheck.isBefore(reservation.getEnd())
                );
    }

    @Override
    public ReservationTableDTO mapToTableDto(Reservation reservation) {
        ReservationTableDTO dto = new ReservationTableDTO();
        dto.setId(reservation.getId());
        dto.setBoardroomNumber(reservation.getBoardroom().getNumber());
        dto.setBoardroomType(reservation.getBoardroom().getRoomType().getDisplayName());
        dto.setBoardroomSize(reservation.getBoardroom().getRoomSize().getDisplayName());
        dto.setStart(reservation.getStart());
        dto.setEnd(reservation.getEnd());
        dto.setStatus(reservation.getStatus());
        return dto;
    }
}
