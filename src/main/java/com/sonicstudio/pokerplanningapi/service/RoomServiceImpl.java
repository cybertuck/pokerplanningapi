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

    UserRepository userRepository;

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
    //@Transactional
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void userJoinRoom(Room room, User user) {
        Set<User> participants = room.getParticipants();
        log.info("participants before add-"+participants.toString());
        participants.add(user);
        log.info("participants after add-"+participants.toString());
        room.setParticipants(participants);
        roomRepository.save(room);
        user.setRoom(room);
        log.info("IN RoomServiceImpl userJoinRoom");
    }

    @Override
    public void userLeaveRoom(Room room, User user) {
        Set<User> participants = room.getParticipants();
        participants.remove(user);
        room.setParticipants(participants);
        roomRepository.save(room);
        user.setRoom(null);
        log.info("IN RoomServiceImpl userLeaveRoom");
    }

    @Override
    public List<Room> getAll() {
        log.info("IN RoomServiceImpl getAll");
        return roomRepository.findAll();
    }
}
