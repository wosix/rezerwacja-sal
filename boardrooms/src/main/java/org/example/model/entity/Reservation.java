package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.ReservationStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "boardroom_id", nullable = false)
    private Boardroom boardroom;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeStart;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeEnd;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation(Account account, Boardroom boardroom, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, ReservationStatus status) {
        this.account = account;
        this.boardroom = boardroom;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.status = status;
    }

}
