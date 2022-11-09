package com.sonicstudio.pokerplanningapi.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.repository.RoomRepository;
import com.sonicstudio.pokerplanningapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room getById(Long id) {
        log.info("IN RoomServiceImpl getById {}", id);
        return roomRepository.findById(id).get();
    }

    @Override
    public void create(Room room) {
        log.info("IN RoomServiceImpl create {}", room);
        roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        log.info("IN RoomServiceImpl delete {}", id);
        roomRepository.delete(getById(id));
    }

    @Override
    public void userJoinRoom(Room room, User user) {
        log.info("IN RoomServiceImpl userJoinRoom");
        room.addUser(user);
        roomRepository.save(room);
    }

    @Override
    public void userLeaveRoom(Room room, User user) {
        log.info("IN RoomServiceImpl userLeaveRoom");
        room.deleteUser(user);
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAll() {
        log.info("IN RoomServiceImpl getAll");
        return roomRepository.findAll();
    }
}
