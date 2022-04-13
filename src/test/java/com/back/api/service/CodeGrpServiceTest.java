package com.back.api.service;

import com.back.api.domain.CodeGrp;
import com.back.api.repository.CodeGrpRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGrpServiceTest {

    @Autowired
    private CodeGrpRepository codeGrpRepository;

    @Test
    void getCodeGrpData() {
        //given
        //when
        var result = codeGrpRepository.findById(2);
        System.out.println("result << " + result);

        //then
        Assertions.assertEquals("사용자 권한", result.get().getCode_grp_nm());
    }

    @Test
    void inputCodeGrp() {
        //given
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCode_grp_nm("메뉴구분");
        //when
        codeGrpRepository.save(codeGrp);
        var result = codeGrpRepository.findById(4);
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals("메뉴구분", result.get().getCode_grp_nm());
    }

    @Test
    void getCodeGrpList() {
        //given

        //when
        var result = codeGrpRepository.findByUseYn();
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void deleteCodeGrp() {
        //given
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCode_grp_id(1);
        codeGrp.setUpdated_id(1);
        codeGrp.setUse_yn("N");

        //when
        codeGrpRepository.updateUseYnById(codeGrp);

        var result = codeGrpRepository.findByUseYn(codeGrp);
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals(3, result.size());
    }


}