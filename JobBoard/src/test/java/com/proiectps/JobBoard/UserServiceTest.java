package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.UserRepository;
import com.proiectps.JobBoard.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    private static final String USERNAME = "john_doe";
    private static final String USERNAME_NOT_EXISTING = "non_existent_user";

    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void init() {
        initMocks(this);
        user = User.builder()
                .id(1L)
                .username(USERNAME)
                .password("password123")
                .email("john.doe@example.com")
                .role(User.Role.USER)
                .build();
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);
    }

    @Test
    void givenExistingUsername_whenFindByUsername_thenFindUser() {

        userServiceImpl = new UserServiceImpl(userRepository);

        User foundUser = userServiceImpl.findByUsername(USERNAME);

        assertNotNull(foundUser);
        assertEquals(USERNAME, foundUser.getUsername());
    }

    @Test
    void givenNonExistingUsername_whenFindByUsername_thenThrowException() {
        when(userRepository.findByUsername(USERNAME_NOT_EXISTING)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userServiceImpl.findByUsername(USERNAME_NOT_EXISTING);
        });

    }
}
