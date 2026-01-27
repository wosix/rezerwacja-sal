package org.example.service;

import org.example.exception.ValidationException;
import org.example.model.Boardroom;
import org.example.model.Equipment;
import org.example.model.dto.BoardroomDTO;
import org.example.repository.BoardroomRepository;

import java.util.List;
import java.util.Optional;

public class BoardroomServiceImpl implements BoardroomService {

    private final BoardroomRepository boardroomRepository;

    public BoardroomServiceImpl() {
        this.boardroomRepository = BoardroomRepository.getInstance();
    }

    @Override
    public List<Boardroom> getAll() {
        return boardroomRepository.findAll();
    }

    @Override
    public Optional<Boardroom> getById(Long id) {
        return boardroomRepository.findById(id);
    }

    @Override
    public void create(BoardroomDTO boardroomDTO) {
        if (!authenticate(boardroomDTO)) {
            throw new ValidationException("Nie wpisano numeru sali");
        }

        Boardroom boardroom = new Boardroom(
                boardroomDTO.getNumber(),
                boardroomDTO.getRoomType(),
                boardroomDTO.getRoomSize(),
                new Equipment(
                        boardroomDTO.isProjector(),
                        boardroomDTO.isWhiteboard(),
                        boardroomDTO.isTvScreen(),
                        boardroomDTO.isVideoConferenceSystem(),
                        boardroomDTO.isAirConditioning(),
                        boardroomDTO.isSoundSystem()
                ),
                boardroomDTO.isAvailable()
        );

        boardroomRepository.save(boardroom);
    }

    private boolean authenticate(BoardroomDTO boardroomDTO) {
        return !boardroomDTO.getNumber().isBlank();
    }

    @Override
    public void delete(Long id) {
        boardroomRepository.delete(id);
    }

    @Override
    public void update(Long id, Boardroom boardroom) {
        boardroomRepository.save(boardroom);
    }

    @Override
    public void updateStatus(Long id, boolean isActive) {
//        boardroomRepository.save();
    }

}
