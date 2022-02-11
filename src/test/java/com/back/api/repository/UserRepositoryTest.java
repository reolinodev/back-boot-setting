package com.back.api.repository;

import com.back.api.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        userRepository.save(new User());
        userRepository.findAll().forEach(System.out::println);
//        System.out.println(userRepository.findAll());
    }
}