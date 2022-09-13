package com.sonicstudio.pokerplanningapi.service;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;

import java.util.List;

public interface UserService {
    User getById (Long id);

    void create(User user);

    void update(User user);

    void delete(Long id);

    List<User> getAll();

    List<User> getAllInRoom(Room room);
}
