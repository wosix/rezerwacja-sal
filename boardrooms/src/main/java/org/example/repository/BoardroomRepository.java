package org.example.repository;

import org.example.model.Boardroom;
import org.example.model.Equipment;
import org.example.model.RoomSize;
import org.example.model.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class BoardroomRepository implements IRepository<Boardroom, Long> {

    private static BoardroomRepository instance;

    private final Map<Long, Boardroom> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private BoardroomRepository() {
        save(new Boardroom("A-02", RoomType.TRAINING, RoomSize.LARGE, new Equipment(), true));
        save(new Boardroom("B-33", RoomType.PRESENTATION, RoomSize.AUDITORIUM, new Equipment(), false));
    }

    public static synchronized BoardroomRepository getInstance() {
        if (instance == null) {
            instance = new BoardroomRepository();
        }
        return instance;
    }

    @Override
    public void save(Boardroom boardroom) {
        if (boardroom.getId() == null) {
            boardroom.setId(idGenerator.getAndIncrement());
        }
        database.put(boardroom.getId(), boardroom);
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public List<Boardroom> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public Optional<Boardroom> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Boardroom> findByNumber(String number) {
        return database.values().stream()
                .filter(boardroom -> number.equals(boardroom.getNumber()))
                .toList();
    }

    public List<Boardroom> findByType(RoomType type) {
        return database.values().stream()
                .filter(boardroom -> type.equals(boardroom.getRoomType()))
                .toList();
    }

    public List<Boardroom> findBySize(RoomSize size) {
        return database.values().stream()
                .filter(boardroom -> size.equals(boardroom.getRoomSize()))
                .toList();
    }

    public List<Boardroom> findByProjector() {
        return database.values().stream()
                .filter(boardroom -> boardroom.getEquipment().hasProjector())
                .toList();
    }

    public boolean existsById(Long id) {
        return database.containsKey(id);
    }

}
