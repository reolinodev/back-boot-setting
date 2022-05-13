package com.back.api.service;

import com.back.api.domain.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin_id("kychoi83");
        userEntity.setUser_nm("최규연");
        userEntity.setUser_pw("1111");
        userEntity.setEmail("reolino@gmail.com");
        userEntity.setCell_phone("01011112222");

        String login_id="kychoi83";

        //given
        userRepository.save(userEntity);
        int result = userRepository.countByLoginId(login_id);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateUserPw() throws NoSuchAlgorithmException {
        //when
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin_id("kychoi83");
        userEntity.setUser_pw("1111");
        userEntity.setUser_pw(CryptUtils.encryptSha256(userEntity.getUser_pw()));

        //given
        userRepository.updateUserPw(userEntity);

        //then
//        String dbData = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
//        Assertions.assertEquals(ecnryptStr, dbData);
    }

    @Test
    void findAll() {
        //when
        UserEntity userEntity = new UserEntity();
        userEntity.setCurrent_page(1);
        userEntity.setPage_per(1);
        userEntity.setSearch_str("kychoi83");

        //given
        var result = userRepository.findAll(userEntity);
        System.out.println("<<"+ result);

        //then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void findByUserId() {
        //when
        int userId = 3;

        //given
        var result = userRepository.findByUserId(userId);
        System.out.println("<<"+ result);

        //then
        Assertions.assertEquals("kychoi83", result.getLogin_id());

    }

    @Test
    void updateUser() throws NoSuchAlgorithmException {
        //when
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(3);
        userEntity.setUser_pw("1111");
        userEntity.setUser_pw(CryptUtils.encryptSha256(userEntity.getUser_pw()));

        //given
        userRepository.updateUser(userEntity);

        //then
//        String dbData = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
//        Assertions.assertEquals(ecnryptStr, dbData);
    }



}