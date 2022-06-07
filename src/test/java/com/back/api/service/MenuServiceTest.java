package com.back.api.service;

import static org.junit.jupiter.api.Assertions.*;

import com.back.api.domain.MenuEntity;
import com.back.api.repository.MenuRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    void inputMenu() {
        //when
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.menu_nm = "권한별 메뉴 관리";
        menuEntity.menu_url = "/menu/authMenuMng";
        menuEntity.menu_type = "url";
        menuEntity.menu_lv = 2;
        menuEntity.ord = 2;
        menuEntity.use_yn = "Y";
        menuEntity.parent_id = 2;

        //given
        var result = menuRepository.save(menuEntity);
        System.out.println("<<"+result);

        //then
    }

    @Test
    void getMenuList() {
        //given
        var result = menuRepository.findAllMenu();
        System.out.println("<<"+result);

        //then
        Assertions.assertEquals("/menu/menuMng", result.get(5).menu_url);
    }

}