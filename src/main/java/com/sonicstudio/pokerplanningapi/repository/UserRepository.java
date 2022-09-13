package com.sonicstudio.pokerplanningapi.repository;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<Room> findByRoom(Room room);

    List<User>findByRoom(Room room);
}
