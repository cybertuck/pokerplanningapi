package com.sonicstudio.pokerplanningapi.room;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.repository.RoomRepository;
import com.sonicstudio.pokerplanningapi.service.RoomService;
import com.sonicstudio.pokerplanningapi.service.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RoomServiceImplTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    static final String ROOM_NAME = "142";
    static final Integer LIFETIME = 14600;

    @Test
    void createDeleteRoom(){
        //Given
        Room room = new Room();
        room.setRoom_number(ROOM_NAME);
        room.setLifetime(LIFETIME);
        room.setScrummaster(43L);

        //When
        roomService.create(room);

        //Then
        assertTrue(roomRepository.findRoomByLifetime(LIFETIME).isPresent());

        //Given
        Long roomid = room.getId();

        //When
        roomService.delete(roomid);

        //Then
        assertFalse(roomRepository.findRoomByLifetime(LIFETIME).isPresent());
    }
}
