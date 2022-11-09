package com.sonicstudio.pokerplanningapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "room")
@ToString
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Room implements Serializable {

    public Room(){}

    public Room(String room_number, Integer lifetime){
        this.room_number = room_number;
        this.lifetime = lifetime;
        this.created_date = LocalDateTime.now();
        this.participants = new HashSet<>();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private String room_number;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> participants = new HashSet<>();

    @Column(name = "scrummaster")
    private Long scrummaster;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "lifetime")
    private Integer lifetime;

    public Room addUser(User user){
        participants.add(user);
        user.setRoom(this);
        return this;
    }

    public Room deleteUser(User user){
        participants.remove(user);
        user.setRoom(null);
        return this;
    }
}
