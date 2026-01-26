package entity;

import java.time.LocalDateTime;

public class Reservation {

    private Long id;
    private Long boardroomId;
    private Account user;
    private LocalDateTime start;
    private LocalDateTime end;
    private ReservationStatus status;

    public Reservation() {
        this.status = ReservationStatus.CONFIRMED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardroomId() {
        return boardroomId;
    }

    public void setBoardroomId(Long boardroomId) {
        this.boardroomId = boardroomId;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    // metoda sprawdzająca nakładanie się terminów
    public boolean overlapsWith(Reservation other) {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

}
