package com.sonicstudio.pokerplanningapi.repository;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findRoomByLifetime(Integer lifetime);

    Optional<User> findByParticipantsIsNotNull();


}
