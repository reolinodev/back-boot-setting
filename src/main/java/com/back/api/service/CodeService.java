package com.back.api.service;

import com.back.api.domain.Code;
import com.back.api.repository.CodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    /**
     * 1. inputCode :코드 데이터를 입력합니다. 코드 값이 동일한게 있는지 확인 합니다. 만약 값이 존재 한다면 -1을 반환합니다.
     * 2. getCodeList : 사용 가능한 코드를 조회합니다.
     * 3. updateCode : 코드의 값을 수정합니다.
     * 4. deleteCode : 코드의 사용여부를 N으로 업데이트 합니다. 수정자는 세션 아이디를 넣어줍니다.
     */

    public int inputCode(Code code) {
        int result = 0;
        int count = codeRepository.countByCodeGrpIdAndCodeVal(code);
        if(count > 0){
            result = -1;
        }else {
            result = codeRepository.save(code);
        }
        return result;
    }

    public List<Code> getCodeList(int id) {
        return codeRepository.findByUseYn(id);
    }

    public int updateCode(Code code) {
        code.setUpdated_id(1); // todo 세션 아이디를 넣어준다.
        return codeRepository.update(code);
    }

    public int deleteCode(int id) {
        Code code = new Code();
        code.setCode_id(id);
        code.setUpdated_id(1); // todo 세션 아이디를 넣어준다.
        code.setUse_yn("N");
        return codeRepository.updateUseYnById(code);
        // todo history 테이블에 내역 입력
    }

    public List<Code> getCodeItemList(String CodeGrpVal) {
        return codeRepository.findByCodeGrpVal(CodeGrpVal);
    }
}
