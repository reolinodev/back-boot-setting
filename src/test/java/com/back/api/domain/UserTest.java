package com.back.api.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("reolino@gmail.com");
        user.setName("reolino");
        user.setIndate(LocalDateTime.now());
        user.setLdate(LocalDateTime.now());
        System.out.println("user = " + user);

        User user3 = new User("reolino3", "reolino3@gmail.com","123456789", "01011112222");
        System.out.println("user3 = " + user3);

        User user2 =  User.builder()
                .name("reolino2")
                .email("reolino2@daum.net")
                .build();

        System.out.println("user2 = " + user2);
    }
}