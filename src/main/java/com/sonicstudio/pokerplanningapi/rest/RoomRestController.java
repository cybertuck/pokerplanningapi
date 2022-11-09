package com.sonicstudio.pokerplanningapi.rest;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.service.RoomService;
import com.sonicstudio.pokerplanningapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomRestController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") Long roomId){
        if(roomId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Room room = roomService.getById(roomId);

        if(room == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> createRoom(@RequestBody @Validated Room room){
        HttpHeaders headers = new HttpHeaders();
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.roomService.create(room);
        return new ResponseEntity<>(room, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> updateRoom(@RequestBody @Validated Room room){
        HttpHeaders headers = new HttpHeaders();
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.roomService.create(room);
        return new ResponseEntity<>(room, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") Long roomId){
        Room room = this.roomService.getById(roomId);
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.roomService.delete(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRooms(){
        List<Room> rooms = this.roomService.getAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @RequestMapping(value = "{room_id}/join/{user_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> userJoinRoom(@PathVariable("room_id") Long roomId, @PathVariable("user_id") Long userId){
        Room room = roomService.getById(roomId);
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getById(userId);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        roomService.userJoinRoom(room, user);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @RequestMapping(value = "{room_id}/leave/{user_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> userLeaveRoom(@PathVariable("room_id") Long roomId, @PathVariable("user_id") Long userId){
        Room room = roomService.getById(roomId);
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getById(userId);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        roomService.userLeaveRoom(room, user);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}
