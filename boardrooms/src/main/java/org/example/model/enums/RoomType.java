package org.example.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoomType {
    MEETING("Spotkań biznesowych"),
    CONFERENCE("Konferencyjna"),
    WORKSHOP("Warsztatowa"),
    TRAINING("Szkoleniowa"),
    PRESENTATION("Prezentacyjna"),
    VIDEO_CONFERENCE("Wideokonferencyjna");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public static RoomType fromDisplayName(String displayName) {
        for (RoomType type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        return CONFERENCE;
    }

    public static String[] getDisplayNames() {
        return Arrays.stream(values())
                .map(RoomType::getDisplayName)
                .toArray(String[]::new);
    }
    
}
