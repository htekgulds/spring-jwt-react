package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Set<User> getUsers() {
        log.info("I'm in the User Service!");

        Set<User> userSet = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }
}
