package com.back.api.service;

import com.back.api.domain.Code;
import com.back.api.domain.CodeEntity;
import com.back.api.repository.CodeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    CodeRepository codeRepository;

    @Test
    void getCodeList() {
        //given
        int code_grp_id = 7;
        //when
        var result = codeRepository.findByCodeGrpId(code_grp_id);

        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void getCodeItemList() {
        //given
        String code_grp_val = "PAGE_PER";
        //when
        var result = codeRepository.findByCodeGrpVal(code_grp_val);

        //then
        Assertions.assertEquals(5, result.size());
    }

    @Test
    void inputCode() {

        //given
        int result = 1;
        List<Code> createdRows = new ArrayList<Code>();

        Code code = new Code();
        code.setCode_grp_id(13);
        code.setCode_nm("테스트1");
        code.setCode_val("TEST1");
        code.setOrd("1");
        code.setBigo("비고");

        createdRows.add(code);

        Code code2 = new Code();
        code2.setCode_grp_id(13);
        code2.setCode_nm("테스트2");
        code2.setCode_val("TEST2");
        code2.setOrd("2");
        code2.setBigo("비고2");

        createdRows.add(code2);

        //when
        for (Code param : createdRows ) {
            int result2 = codeRepository.save(param);
            if(result2 <= 0) result = 0;
        }

        //then
        Assertions.assertEquals(-1, result);
    }
}