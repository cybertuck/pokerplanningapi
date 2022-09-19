package com.sonicstudio.pokerplanningapi.service;

import com.sonicstudio.pokerplanningapi.model.Room;
import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public User getById(Long id) {
        log.info("IN UserServiceImpl getById {}", id);
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public void create(User user) {
        log.info("IN UserServiceImpl create {}", user);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        log.info("IN UserServiceImpl update {}", user);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        log.info("IN UserServiceImpl delete {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        log.info("IN UserServiceImpl getAll");
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> getAllInRoom(Room room) {
        log.info("IN UserServiceImpl getAll");
        List<User> users = userRepository.findByRoom(room);
        return users;
    }
}
