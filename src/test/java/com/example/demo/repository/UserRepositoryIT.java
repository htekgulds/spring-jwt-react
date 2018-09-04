package com.example.demo.repository;

import com.example.demo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIT {

    @Inject
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUsername() {
        Optional<User> userOptional = userRepository.findByUsername("htds");
        assertEquals("hasan", userOptional.get().getName());
    }

    @Test
    public void findByUsernameMahmut() {
        Optional<User> userOptional = userRepository.findByUsername("mtuncer");
        assertEquals("mahmut", userOptional.get().getName());
    }
}