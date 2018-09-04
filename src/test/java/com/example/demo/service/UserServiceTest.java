package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUSers() {
        User user = new User("hasan", "htds", "htds@local.com", "123456");
        List<User> userData = new ArrayList<>();
        userData.add(user);

        when(userRepository.findAll()).thenReturn(userData);

        Set<User> users = userService.getUsers();

        assertEquals(users.size(), 1);
        verify(userRepository, times(1)).findAll();
    }
}