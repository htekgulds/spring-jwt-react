package com.example.demo.controller;

import com.example.demo.repository.PollRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.service.PollService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PollRepository pollRepository;
    @Mock
    VoteRepository voteRepository;
    @Mock
    PollService pollService;

    UserController userController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userRepository, pollRepository, voteRepository, pollService);
    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(get("/api/user/checkUsernameAvailability?username=htds"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkUsernameAvailability() {
        String username = "hasantekgul";

        when(userRepository.existsByUsername(username)).thenReturn(true);
        assertEquals(false, userController.checkUsernameAvailability(username).getAvailable());
        verify(userRepository, times(1)).existsByUsername(username);
    }
}