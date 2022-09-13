package com.sonicstudio.pokerplanningapi.room;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.repository.RoomRepository;
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
    private RoomRepository roomRepository;

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

        //When
        roomService.userJoinRoom(room, user);

        //Then
        assertTrue(roomRepository.findByParticipantsIsNotNull().isPresent());
        assertEquals(1, userRepository.findByRoom(room).size());
    }
}
