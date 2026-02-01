package org.example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.RoomSize;
import org.example.model.enums.RoomType;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "boardrooms")
public class Boardroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    private RoomSize roomSize;

    @Embedded
    private Equipment equipment;

    private boolean available;

    @OneToMany(mappedBy = "boardroom")
    private Set<Reservation> reservations;

    public Boardroom(String number, RoomType type, RoomSize size, Equipment equipment, boolean isAvailable) {
        this.number = number;
        this.roomType = type;
        this.roomSize = size;
        this.equipment = equipment;
        this.available = isAvailable;
    }

}
