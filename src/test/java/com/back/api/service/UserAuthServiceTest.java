package com.back.api.service;

import com.back.api.domain.UserAuth;
import com.back.api.domain.UserAuthEntity;
import com.back.api.repository.UserAuthRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAuthServiceTest {

    @Autowired
    UserAuthRepository userAuthRepository;



    @Test
    void getInputAuthUser() {
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_id = 2;
        userAuthEntity.page_per = 10;
        userAuthEntity.current_page = 1;
        userAuthEntity.setSearch_str("kychoi83");
        userAuthEntity.setStart_idx(userAuthEntity.page_per, userAuthEntity.current_page);

        //given
        var result = userAuthRepository.findByAuthIdNotAndUseYn(userAuthEntity);
        System.out.println("<<"+result);

        //then
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void getInputAuthUserCount() {
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_id = 2;

        //given
        int result = userAuthRepository.countByAuthIdNotAndUseYn(userAuthEntity);
        //then
        Assertions.assertEquals(12, result);
    }

    @Test
    void inputUserAuth(){
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_id = 1;
        userAuthEntity.updated_id = 3;
        userAuthEntity.user_arr = new int[]{3, 8};

        int[] arr = userAuthEntity.user_arr;
        for (int j : arr) {
            userAuthEntity.user_id = j;
            userAuthRepository.save(userAuthEntity);
        }

        //then
    }

    @Test
    void getAuthUserList() {
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
//        userAuthEntity.auth_id = 2;
        userAuthEntity.page_per = 10;
        userAuthEntity.current_page = 1;
        userAuthEntity.auth_role = "ADMIN";
//        userAuthEntity.setSearch_str("kychoi83");
        userAuthEntity.setStart_idx(userAuthEntity.page_per, userAuthEntity.current_page);

        //given
        var result = userAuthRepository.findAll(userAuthEntity);
        System.out.println("<<"+result);

        //then
        Assertions.assertEquals(10, result.size());
    }

    @Test
    void getAuthUserCount() {
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_role = "ADMIN";
        userAuthEntity.setSearch_str("kychoi83");

        //given
        var result = userAuthRepository.countAll(userAuthEntity);
        System.out.println("<<"+result);

        //then
        Assertions.assertEquals(20, result);
    }


    /**
     * 사용자의 권한을 삭제합니다.
     */
    @Test
    void deleteUserAuth() {
        //when
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.auth_id = 2;
        userAuthEntity.updated_id = 3;
        userAuthEntity.user_arr = new int[]{16, 17};

        //given
        var result = userAuthRepository.deleteUserAuth(userAuthEntity);

        //then
//        Assertions.assertEquals(20, result);
    }

}