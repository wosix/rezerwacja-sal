package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.RoomSize;
import org.example.model.enums.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class BoardroomDTO {

    private String number;
    private RoomType roomType;
    private RoomSize roomSize;
    private boolean projector;
    private boolean whiteboard;
    private boolean tvScreen;
    private boolean videoConferenceSystem;
    private boolean airConditioning;
    private boolean soundSystem;
    private boolean available;

}
