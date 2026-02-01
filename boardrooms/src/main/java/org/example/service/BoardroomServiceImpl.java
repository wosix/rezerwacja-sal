package org.example.service;

import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.dto.BoardroomDTO;
import org.example.model.entity.Boardroom;
import org.example.model.entity.Equipment;
import org.example.repository.BoardroomRepositoryImpl;

import java.util.List;

public class BoardroomServiceImpl implements BoardroomService {

    private final BoardroomRepositoryImpl boardroomRepository;

    public BoardroomServiceImpl() {
        this.boardroomRepository = new BoardroomRepositoryImpl();
    }

    @Override
    public List<Boardroom> getAll() {
        return boardroomRepository.findAll();
    }

    @Override
    public Boardroom getById(Long id) {
        return boardroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono sali o takim id: " + id));
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
//        boardroomRepository.delete(id);
    }

    @Override
    public void update(Long id, Boardroom boardroom) {
//        boardroomRepository.save(boardroom);
    }

    @Override
    public void active(Long id) {
        Boardroom boardroom = getById(id);
        boardroom.setAvailable(true);
        boardroomRepository.save(boardroom);
    }

    @Override
    public void deactive(Long id) {
        Boardroom boardroom = getById(id);
        boardroom.setAvailable(false);
        boardroomRepository.save(boardroom);
    }

}
