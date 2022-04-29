package com.back.api.service;

import com.back.api.domain.Auth;
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
        Auth auth = new Auth();
        auth.auth_nm = "Super Admin";
        auth.auth_role = "ADMIN";
        auth.bigo = "모든 권한을 가진 사용자";
        auth.ord = "1";

        //given
        authRepository.save(auth);
        var result = authRepository.findByUseYn(auth);

        //then
        Assertions.assertEquals("Super Admin", result.get(0).getAuth_nm());
    }


}