package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.ReservationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationTableDTO {

    private String boardroomNumber;
    private String boardroomType;
    private String boardroomSize;
    private LocalDateTime start;
    private LocalDateTime end;
    private ReservationStatus status;

}
