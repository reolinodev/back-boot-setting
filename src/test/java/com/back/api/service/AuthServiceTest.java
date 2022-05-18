package com.back.api.service;

import com.back.api.domain.Auth;
import com.back.api.domain.AuthEntity;
import com.back.api.repository.AuthRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthRepository authRepository;

    @Test
    void inputAuth(){
        //when
        AuthEntity authEntity = new AuthEntity();
        authEntity.auth_nm = "Admin";
        authEntity.auth_role = "ADMIN";
        authEntity.bigo = "운영자";
        authEntity.ord = "2";

        //given
        authRepository.save(authEntity);
        var result = authRepository.findByUseYn(authEntity);

        //then
        Assertions.assertEquals("Super Admin", result.get(0).getAuth_nm());
    }


    @Test
    void updateAuth(){
        //when
        AuthEntity authEntity = new AuthEntity();
        authEntity.auth_nm = "Admin";
        authEntity.auth_role = "ADMIN";
        authEntity.bigo = "운영자";
        authEntity.ord = "2";
        authEntity.auth_id = 2;

        //given
        authRepository.updateAuth(authEntity);
        var result = authRepository.findByAuthId(2);

        //then
        Assertions.assertEquals("Admin", result.getAuth_nm());
    }


    @Test
    void findByAll(){
        //when
        AuthEntity authEntity = new AuthEntity();
        authEntity.use_yn = "Y";

        //given
        var result = authRepository.findAll(authEntity);

        //then
        Assertions.assertEquals(2, result.size());
    }



}