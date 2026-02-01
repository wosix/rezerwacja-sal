package org.example.repository;

import org.example.model.entity.Boardroom;
import org.example.model.enums.RoomSize;
import org.example.model.enums.RoomType;
import org.example.repository.jpa.IRepository;

import java.util.List;

public interface BoardroomRepository extends IRepository<Boardroom, Long> {

    List<Boardroom> findByNumberLike(String number);

    List<Boardroom> findByNumber(String number);

    List<Boardroom> findByType(RoomType type);

    List<Boardroom> findBySize(RoomSize size);

    List<Boardroom> findByProjector();

    List<Boardroom> findByAirConditioning();

}
