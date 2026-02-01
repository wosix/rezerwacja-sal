package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.dto.ReservationTableDTO;
import org.example.model.entity.Account;
import org.example.model.entity.Boardroom;
import org.example.model.entity.Reservation;
import org.example.model.enums.ReservationStatus;
import org.example.repository.ReservationRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepositoryImpl reservationRepository;

    private final AccountServiceImpl accountService;

    public ReservationServiceImpl() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getByUserId(Long userId) {
        return reservationRepository.findAllByAccountId(userId);
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono rezerwacji o id: " + id));
    }

    @Override
    public void delete(Long id) {
//        reservationRepository.delete(id);
    }

    @Override
    public void update(Long id, Reservation reservation) {
//        reservationRepository.save(reservation);
    }

    @Override
    public void createReservation(Long userId, Boardroom boardroom, LocalDateTime dateTimeStart) {
        LocalDateTime dateTimeEnd = dateTimeStart.plusMinutes(59);
        Account account = accountService.getById(userId);
        Reservation reservation = new Reservation(
                account,
                boardroom,
                dateTimeStart,
                dateTimeEnd,
                ReservationStatus.CONFIRMED
        );
        reservationRepository.save(reservation);
    }

    @Override
    public void cancel(Long id) {
        Reservation reservation = getById(id);
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    @Override
    public boolean isHourBooked(Long boardroomId, LocalDateTime date, int hour) {
        LocalDateTime hourToCheck = date.withHour(hour).withMinute(0);
        return reservationRepository.findByBoardroomId(boardroomId).stream()
                .anyMatch(reservation ->
                        reservation.getStatus() != ReservationStatus.CANCELLED &&
                                !hourToCheck.isBefore(reservation.getDateTimeStart()) &&
                                hourToCheck.isBefore(reservation.getDateTimeEnd())
                );
    }

    @Override
    public ReservationTableDTO mapToTableDto(Reservation reservation) {
        ReservationTableDTO dto = new ReservationTableDTO();
        dto.setId(reservation.getId());
        dto.setBoardroomNumber(reservation.getBoardroom().getNumber());
        dto.setBoardroomType(reservation.getBoardroom().getRoomType().getDisplayName());
        dto.setBoardroomSize(reservation.getBoardroom().getRoomSize().getDisplayName());
        dto.setStart(reservation.getDateTimeStart());
        dto.setEnd(reservation.getDateTimeEnd());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

}
