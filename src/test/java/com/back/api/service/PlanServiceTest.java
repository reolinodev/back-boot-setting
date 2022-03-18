package com.back.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @Test
    void findShopAll() {
        /**
         * condition :  검색어를 갈비로 넣는다.
         * expected : 조회된 결과가 주소와 이미지가 널이 아니다.
         */
        var result = planService.findShopAll("갈비");
        System.out.println("result = " + result);
        Assertions.assertNotNull(result.getAddress());
        Assertions.assertNotNull(result.getImageLink());
    }

}