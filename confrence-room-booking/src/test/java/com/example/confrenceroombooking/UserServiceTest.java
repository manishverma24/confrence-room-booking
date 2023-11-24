package com.example.confrenceroombooking;

import com.example.confrenceroombooking.dto.UserRepository;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // create a user
        User user = new User();
        user.setUserId("userId");
        user.setName("userName");

        User createdUser = userService.createUser(user);

        // verify
        assertNotNull(createdUser.getId());
        assertEquals(user.getUserId(), createdUser.getUserId());
        assertEquals(user.getName(), user.getName());
    }
}
