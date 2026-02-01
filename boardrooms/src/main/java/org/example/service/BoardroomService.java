package org.example.service;

import org.example.model.dto.BoardroomDTO;
import org.example.model.entity.Boardroom;

import java.util.List;

public interface BoardroomService {

    List<Boardroom> getAll();

    Boardroom getById(Long id);

    void create(BoardroomDTO boardroomDTO);

    void delete(Long id);

    void update(Long id, Boardroom boardroom);

    void active(Long id);

    void deactive(Long id);

}
