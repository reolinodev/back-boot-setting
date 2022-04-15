package com.back.api.service;

import com.back.api.domain.Code;
import com.back.api.repository.CodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    CodeRepository codeRepository;

    @Test
    void inputCode() {

        //given
        int result = 0;
        Code code = new Code();
        code.setCode_grp_id(7);
        code.setCode_nm("미사용");
        code.setCode_val("N");
        code.setOrd("11");
        code.setBigo("미사용 처리");

        //when
        int count = codeRepository.countByCodeGrpIdAndCodeVal(code);
        if(count > 0){
            result = -1;
        }else {
            result = codeRepository.save(code);
        }

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void getCodeList() {
        //given

        //when
        var result = codeRepository.findByUseYn(7);
        System.out.println("result << " + result);

        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void updateCode() {
        //given
        Code code = new Code();
        code.setCode_id(6);
        code.setCode_grp_id(7);
        code.setCode_nm("사용");
        code.setOrd("2");
        code.setUpdated_id(1);

        //when
        codeRepository.update(code);
        var result = codeRepository.findById(6);
        System.out.println("result << " + result);

        //then
        Assertions.assertEquals("사용", result.get().getCode_nm());
    }

    @Test
    void deleteCode() {
        //given
        Code code = new Code();
        code.setUpdated_id(1);
        code.setCode_id(6);
        code.setUse_yn("N");
        //when
        codeRepository.updateUseYnById(code);
        var result = codeRepository.findById(6);
        System.out.println("result << " + result);
        //then
        Assertions.assertEquals("N", result.get().getUse_yn());
    }

}