package com.sonicstudio.pokerplanningapi.service;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {

    Room getById(Long id);

    void create(Room room);

    void delete(Long id);

    void userJoinRoom(Room room, User user);

    void userLeaveRoom(Room room, User user);

    List<Room> getAll();

}
