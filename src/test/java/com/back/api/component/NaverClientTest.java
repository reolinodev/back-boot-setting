package com.back.api.component;

import com.back.api.domain.naver.SearchImageReq;
import com.back.api.domain.naver.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocal() {
        //condition : 검색어를 갈비집으로 넣는다.
        //expected : 조회된 결과가 카테고리가 널이 아니다
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }

    @Test
    public void searchImage() {
        //condition : 검색어를 갈비집으로 넣는다.
        //expected : 조회된 결과가 이미지가 널이 아니다
        var search = new SearchImageReq();
        search.setQuery("갈비집");
        var result = naverClient.searchImage(search);
        System.out.println(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getLink());
    }

}