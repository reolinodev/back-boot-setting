package com.back.support;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptUtilsTest {

    @Test
    void encrypt() throws IOException {
        //when
        String str = "test";
        //given
        String ecnryptStr = CryptUtils.encrypt(str);
        System.out.println("=="+ecnryptStr);
    }

    @Test
    void encryptSha256() throws NoSuchAlgorithmException {
        //when
        String str = "1111";
        //given
        String ecnryptStr = CryptUtils.encryptSha256(str);
        System.out.println("<<"+ecnryptStr);
        //then
        String dbData = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        Assertions.assertEquals(ecnryptStr, dbData);
    }
}