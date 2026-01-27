package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Boardroom {

    private Long id;
    private String number;
    private RoomType roomType;
    private RoomSize roomSize;
    private Equipment equipment;
    private boolean available;

    public Boardroom(String number, RoomType type, RoomSize size, Equipment equipment, boolean isAvailable) {
        this.number = number;
        this.roomType = type;
        this.roomSize = size;
        this.equipment = equipment;
        this.available = isAvailable;
    }

}
