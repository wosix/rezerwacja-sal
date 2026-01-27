package org.example.service;

import org.example.model.Boardroom;

import java.util.List;

public class BoardroomServiceImpl implements BoardroomService {

    @Override
    public List<Boardroom> getAll() {
        return List.of();
    }

    @Override
    public Boardroom getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Boardroom update(int id, Boardroom boardroom) {
        return null;
    }

    @Override
    public Boardroom updateStatus(int id, boolean isActive) {
        return null;
    }

}
