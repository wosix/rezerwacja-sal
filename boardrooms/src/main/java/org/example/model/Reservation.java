package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reservation {

    private Long id;
    private Long userId;
    private Boardroom boardroom;
    private LocalDateTime start;
    private LocalDateTime end;
    private ReservationStatus status;

    public Reservation(Long userId, Boardroom boardroom, LocalDateTime start, LocalDateTime end, ReservationStatus status) {
        this.userId = userId;
        this.boardroom = boardroom;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    // metoda sprawdzająca nakładanie się terminów
    public boolean overlapsWith(Reservation other) {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

}
