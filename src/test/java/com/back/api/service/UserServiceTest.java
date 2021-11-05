package com.back.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.back.api.dto.User;
import com.back.repository.UserMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    public void findAll(){
        // given
        User user = new User();

        // when
        List<User> list =  userService.findAll(user);
        
        // then
        assertEquals(2, list.size());
    }


}
    