package com.back.service;

import static org.junit.jupiter.api.Assertions.*;

import com.back.api.domain.User;
import com.back.repository.LoginRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginRepository loginRepository;

    @Test
    void checkLoginId() {
        //when
        User user = new User();
        user.setLogin_id("kychoi83");
        //given
        int result = loginRepository.countByLoginId(user);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void checkUserPw() throws NoSuchAlgorithmException {
        //when
        User user = new User();
        user.setLogin_id("kychoi83");
        user.setUser_pw(CryptUtils.encryptSha256("1111"));

        //given
        int result = loginRepository.countByLoginIdAndUserPw(user);
        //then
        Assertions.assertEquals(1, result);
    }


}