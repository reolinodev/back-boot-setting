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
    void inputCodeGrp() {
        //given
        int result = 0;
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCode_grp_nm("권한구분");
        codeGrp.setCode_grp_val("");

        //when
        int count = codeGrpRepository.countByCodeGrpVal(codeGrp);
        if(count > 0){
            result = -1;
        }else {
            result = codeGrpRepository.save(codeGrp);
        }

        //then
        Assertions.assertEquals(-1, result);
    }

    @Test
    void getCodeGrpList() {
        //given

        //when
        var result = codeGrpRepository.findByUseYn();
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals(1, result.size());
    }


    @Test
    void updateCodeGrp() {
        //given
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setUpdated_id(1);
        codeGrp.setCode_grp_nm("권한 구분");
        codeGrp.setCode_grp_id(5);

        //when
        codeGrpRepository.update(codeGrp);

        var result = codeGrpRepository.findById(5);
        System.out.println("result << " + result);

        //then
        Assertions.assertEquals("권한 구분", result.get().getCode_grp_nm());
    }


    @Test
    void deleteCodeGrp() {
        //given
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCode_grp_id(5);
        codeGrp.setUpdated_id(1);
        codeGrp.setUse_yn("N");

        //when
        codeGrpRepository.updateUseYnById(codeGrp);

        var result = codeGrpRepository.findById(5);
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals("N", result.get().getUse_yn());
    }

}