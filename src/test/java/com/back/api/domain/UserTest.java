package com.back.api.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("reolino@gmail.com");
        user.setName("reolino");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
        System.out.println("user = " + user);

        User user1 = new User("reolino1", "reolino1@naver.com");
        System.out.println("user1 = " + user1);

        User user2 =  User.builder()
                .name("reolino2")
                .email("reolino2@daum.net")
                .build();

        System.out.println("user2 = " + user2);
    }

}