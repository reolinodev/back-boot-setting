package com.back.api.service;

import com.back.api.domain.Code;
import com.back.api.domain.CodeEntity;
import com.back.api.repository.CodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    /**
     * 1. getCodeList : 그룹코드에 해당하는 코드리스트를 조회합니다.
     * 2. getCodeItemList : 주로 콤보박스에서 가져오는 사용가능한 코드의 리스트를을 조회합니다.
     * 3. inputCode :코드 데이터를 입력합니다.
     * 4. updateCode : 코드의 값을 수정합니다.
     * 5. deleteCode : 코드를 삭제합니다.
     */

    public List<CodeEntity> getCodeList(int code_grp_id) {
        return codeRepository.findByCodeGrpId(code_grp_id);
    }

    public List<CodeEntity> getCodeItemList(String CodeGrpVal) {
        return codeRepository.findByCodeGrpVal(CodeGrpVal);
    }

    public int inputCode(CodeEntity codeEntity) {
        Code[] createdRows = codeEntity.created_rows;
        int result = 1;

        for (Code code : createdRows ) {
            int result2 = codeRepository.save(code);
            if(result2 <= 0) result = 0;
        }
        return result;
    }


    public int updateCode(CodeEntity codeEntity) {
        Code[] updatedRows = codeEntity.updated_rows;
        int result = 1;

        for (Code code : updatedRows ) {
            int result2 = codeRepository.update(code);
            if(result2 <= 0) result = 0;
        }

        return result;
    }

    public int deleteCode(CodeEntity codeEntity) {
        Code[] deletedRows = codeEntity.deleted_rows;
        int result = 1;

        for (Code code : deletedRows ) {
            int code_id = code.code_id;
            int result2 = codeRepository.deleteById(code_id);
            if(result2 <= 0) result = 0;
        }
        return result;
    }
}
