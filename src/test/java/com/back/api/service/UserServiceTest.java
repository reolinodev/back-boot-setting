package com.back.api.service;

import com.back.api.domain.User;
import com.back.api.repository.UserRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void checkLoginId() {
        //when
        String login_id="kychoi83";
        //given
        int result = userRepository.countByLoginId(login_id);
        //then
        Assertions.assertEquals(0, result);
    }

    @Test
    void save() {
        //when
        User user = new User();
        user.setLogin_id("kychoi83");
        user.setUser_nm("최규연");
        user.setUser_pw("1111");
        user.setEmail("reolino@gmail.com");
        user.setCell_phone("01011112222");

        String login_id="kychoi83";

        //given
        userRepository.save(user);
        int result = userRepository.countByLoginId(login_id);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateUserPw() throws NoSuchAlgorithmException {
        //when
        User user = new User();
        user.setLogin_id("kychoi83");
        user.setUser_pw("1111");
        user.setUser_pw(CryptUtils.encryptSha256(user.getUser_pw()));

        //given
        userRepository.updateUserPw(user);

        //then
//        String dbData = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
//        Assertions.assertEquals(ecnryptStr, dbData);
    }

}