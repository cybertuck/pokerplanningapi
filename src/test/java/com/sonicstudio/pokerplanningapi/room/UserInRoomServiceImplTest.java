package com.sonicstudio.pokerplanningapi.room;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.repository.UserRepository;
import com.sonicstudio.pokerplanningapi.service.RoomService;
import com.sonicstudio.pokerplanningapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserInRoomServiceImplTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    static final String ROOM_NAME = "133";
    static final Integer LIFETIME = 11300;

    @Test
    void joinUserToRoom(){
        //Given
        Room room = new Room();
        room.setRoom_number(ROOM_NAME);
        room.setLifetime(LIFETIME);
        room.setScrummaster(23L);
        roomService.create(room);

        User user = new User();
        user.setName("michael");
        user.setUid("michael534598649");
        userService.create(user);

        User user2 = new User();
        user2.setName("peter");
        user2.setUid("peter534598923");
        userService.create(user2);

        //When
        roomService.userJoinRoom(room, user);
        roomService.userJoinRoom(room, user2);

        //Then
        assertEquals(2, userRepository.findByRoom(room).size());

        //When
        roomService.userLeaveRoom(room, user);

        //Then
        assertEquals(1, userRepository.findByRoom(room).size());
    }
}
