package com.example.confrenceroombooking.service.impl;

import com.example.confrenceroombooking.dto.UserRepository;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (Objects.nonNull(user)) {
            userRepository.save(user);
        }
        return user;
    }
}
