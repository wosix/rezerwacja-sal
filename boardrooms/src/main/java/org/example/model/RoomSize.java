package org.example.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoomSize {
    SMALL("Mała (do 10 osób)"),
    MEDIUM("Średnia (10-30 osób)"),
    LARGE("Duża (30-50 osób)"),
    AUDITORIUM("Audytorium");

    private final String displayName;

    RoomSize(String displayName) {
        this.displayName = displayName;
    }

    public static RoomSize fromDisplayName(String displayName) {
        for (RoomSize size : values()) {
            if (size.displayName.equals(displayName)) {
                return size;
            }
        }
        return MEDIUM;
    }

    public static String[] getDisplayNames() {
        return Arrays.stream(values())
                .map(RoomSize::getDisplayName)
                .toArray(String[]::new);
    }

}
