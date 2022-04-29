package com.back.service;

import com.back.api.domain.LoginEntity;
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
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setLogin_id("kychoi83");
        //given
        int result = loginRepository.countByLoginId(loginEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void checkUserPw() throws NoSuchAlgorithmException {
        //when
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setLogin_id("kychoi83");
        loginEntity.setUser_pw(CryptUtils.encryptSha256("1111"));

        //given
        int result = loginRepository.countByLoginIdAndUserPw(loginEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void getLoginId() {
        //when
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setLogin_id("kychoi83");
        //given
        LoginEntity result = loginRepository.findByLoginId(loginEntity);
        //then
        Assertions.assertEquals(3, result.getUser_id());

    }

    @Test
    void inputLoginHistory() {
        //when
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUser_id(3);
        loginEntity.setLogin_device("PC");
        loginEntity.setDevice_browser("Chrome");
        loginEntity.setLogin_id("kychoi83");

        //given
        loginRepository.saveLoginHistory(loginEntity);
    }

    @Test
    void updateLastLoginAt() {
        //when
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUser_id(3);
        loginEntity.setLogin_id("kychoi83");

        //given
        loginRepository.saveLastLoginAt(loginEntity);
        //then
        LoginEntity result = loginRepository.findByLoginId(loginEntity);
        System.out.println(result);
    }


}