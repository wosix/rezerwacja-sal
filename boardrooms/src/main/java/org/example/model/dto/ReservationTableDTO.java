package org.example.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ReservationTableDTO {

    private Long id;
    private String boardroomNumber;
    private String boardroomType;
    private String boardroomSize;
    private LocalDateTime start;
    private LocalDateTime end;
    private ReservationStatus status;

    private static String dateTimePattern = "dd/MM/yyyy - HH:mm";

    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern(dateTimePattern));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern(dateTimePattern));
    }

}
