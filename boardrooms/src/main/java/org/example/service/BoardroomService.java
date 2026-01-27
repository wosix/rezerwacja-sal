package org.example.service;

import org.example.model.Boardroom;
import org.example.model.dto.BoardroomDTO;

import java.util.List;

public interface BoardroomService {

    List<Boardroom> getAll();

    Boardroom getById(int id);

    void create(BoardroomDTO boardroomDTO);

    void delete(int id);

    Boardroom update(int id, Boardroom boardroom);

    Boardroom updateStatus(int id, boolean isActive);

}
