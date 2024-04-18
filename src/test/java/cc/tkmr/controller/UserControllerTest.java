/*
 * Copyright (c) 2024 Daniel I. Tikamori. All rights reserved.
 */

package cc.tkmr.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cc.tkmr.controller.dto.UserDto;
import cc.tkmr.domain.model.User;
import cc.tkmr.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        when(userService.findAll()).thenReturn(List.of(mock(User.class)));
        when(userService.create(any(User.class))).thenReturn(mock(User.class));
        when(userService.update(any(Long.class), any(User.class))).thenReturn(mock(User.class));
    }

    @Test
    void findAll() {
        ResponseEntity<List<UserDto>> responseEntity = userController.findAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotEmpty();
    }

    @Test
    void findById() {
        ResponseEntity<UserDto> responseEntity = userController.findById(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void create() {
        ResponseEntity<UserDto> responseEntity = userController.create(new UserDto());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void update() {
        ResponseEntity<UserDto> responseEntity = userController.update(1L, new UserDto());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void delete() {
        userController.delete(1L);

        verify(userService).delete(1L);
    }
}