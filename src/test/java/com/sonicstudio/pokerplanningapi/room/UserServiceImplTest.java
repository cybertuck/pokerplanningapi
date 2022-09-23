package com.sonicstudio.pokerplanningapi.room;

import com.sonicstudio.pokerplanningapi.model.User;
import com.sonicstudio.pokerplanningapi.repository.UserRepository;
import com.sonicstudio.pokerplanningapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    static final String USER_UID = "olaf534598346";


    @Test
    void createDeleteUser() {

        //Given
        User user = new User();
        user.setName("olaf");
        user.setUid(USER_UID);

        //When
        userService.create(user);

        //Then
        assertTrue(userRepository.findByUidEqualsIgnoreCase(user.getUid()).isPresent());

        //Given
        Long userid = user.getId();

        //When
        userService.delete(userid);
    }
}