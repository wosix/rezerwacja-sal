package org.example.service;

import org.example.model.Boardroom;
import org.example.model.dto.BoardroomDTO;

import java.util.List;
import java.util.Optional;

public interface BoardroomService {

    List<Boardroom> getAll();

    Optional<Boardroom> getById(Long id);

    void create(BoardroomDTO boardroomDTO);

    void delete(Long id);

    void update(Long id, Boardroom boardroom);

    void updateStatus(Long id, boolean isActive);

}
