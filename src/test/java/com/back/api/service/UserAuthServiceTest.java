package com.back.api.service;

import com.back.api.domain.UserAuth;
import com.back.api.repository.UserAuthRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAuthServiceTest {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Test
    void inputUserAuth(){
        //when
        UserAuth userAuth = new UserAuth();
        userAuth.auth_id = 1;
        userAuth.user_id = 3;

        //given
        userAuthRepository.save(userAuth);

        //then
    }

}