package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.payload.*;
import com.example.demo.repository.PollRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.PollService;
import com.example.demo.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final PollService pollService;

    public UserController(
            UserRepository userRepository,
            PollRepository pollRepository,
            VoteRepository voteRepository,
            PollService pollService) {
        this.userRepository = userRepository;
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
        this.pollService = pollService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getName());

        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getCreatedAt(),
                pollCount,
                voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(
            @PathVariable(value = "username") String username,
            @CurrentUser UserPrincipal currentUser,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }

    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(
            @PathVariable(value = "username") String username,
            @CurrentUser UserPrincipal currentUser,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }
}
