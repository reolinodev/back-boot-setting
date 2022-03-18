package com.back.api.service;

import com.back.api.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        /**
         * condition :  name = AAA
         * expected : phone이 01011112222로 조회된다
         */
        User user = new User();
        user.setName("AAA");
        var result = userService.findAll(user);
        System.out.println("result = " + result);
        Assertions.assertEquals("01011112222", result.get(0).getPhone());

        /**
         * condition :  email = BBB@gmail.com
         * expected : name이 BBB로 조회
         */
        user = new User();
        user.setEmail("BBB@gmail.com");
        var result2 = userService.findAll(user);
        System.out.println("result2 = " + result2);
        Assertions.assertEquals("BBB", result2.get(0).getName());

        /**
         * condition : 조건절 없음
         * expected : 2개의 결과값이 조회된다.
         */

        user = new User();
        var result3 = userService.findAll(user);
        System.out.println("result3 = " + result3);
        Assertions.assertEquals(2, result3.size());
    }

    @Test
    void findById() {
        /**
         * condition :  id = 1
         * expected : name은 AAA로 조회된다.
         */
        var result = userService.findById(1);
        System.out.println("result = " + result);
        Assertions.assertEquals("AAA", result.get().getName());
    }

    @Test
    void save() {
        /**
         * condition :
         * name = CCC, email = CCC@gmail.com, birth = 20180901,
         * password = 1111111111, phone = 01011113333
         * expected : 저장후 조회 결과가 CCC가 존재한다.
         */
        User user = new User();
        user.setName("CCC");
        user.setEmail("CCC@gmail.com");
        user.setBirth("20180901");
        user.setPassword("1111111111");
        user.setPhone("01011113333");
        userService.save(user);

        var result = userService.findAll(user);
        System.out.println("result = " + result);
        Assertions.assertEquals("CCC@gmail.com", result.get(0).getEmail());
    }

    @Test
    void update() {
        /**
         * id = 1, name = DDD, password = AAAA@@@@####, phone = 01011113333
         * expected : DDD, AAAA@@@@####, 01011113333이 정상적으로 변경된다.
         */
        User user = new User();
        user.setId(1);
        user.setName("DDD");
        user.setPassword("AAAA@@@@####");
        user.setPhone("01011113333");
        userService.update(user);

        var result = userService.findById(1);
        System.out.println("result = " + result);
        Assertions.assertEquals("DDD", result.get().getName());
        Assertions.assertEquals("AAAA@@@@####", result.get().getPassword());
        Assertions.assertEquals("01011113333", result.get().getPhone());
    }

    @Test
    void deleteById() {
        /**
         * condition : id = 2
         * expected : 삭제된 후의 전체 조회 사이즈가 1이다.
         */
        User user = new User();
        var result = userService.findAll(user);
        System.out.println("result = " + result);
        Assertions.assertEquals(2, result.size());
        userService.deleteById(2);
        var result2 = userService.findAll(user);
        System.out.println("result2 = " + result);
        Assertions.assertEquals(1, result2.size());
    }
}