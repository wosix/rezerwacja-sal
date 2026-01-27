package org.example.service;

import org.example.model.Boardroom;

import java.util.List;

public interface BoardroomService {

    List<Boardroom> getAll();

    Boardroom getById(int id);

    void delete(int id);

    Boardroom update(int id, Boardroom boardroom);

    Boardroom updateStatus(int id, boolean isActive);

}
