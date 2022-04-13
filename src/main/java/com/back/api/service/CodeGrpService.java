package com.back.api.service;

import com.back.api.domain.CodeGrp;
import com.back.api.repository.CodeGrpRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeGrpService {

    private final CodeGrpRepository codeGrpRepository;

    /**
     * 1. inputCodeGrp :코드 그룹 데이터를 입력합니다.
     * 2. getCodeGrpList : 사용 가능한 코드 그룹을 조회합니다.
     * 3. deleteCodeGrp : 코드 그룹의 사용여부를 N으로 업데이트 합니다. 하위 코드들의 사용여부 역시 N으로 업데이트 합니다. 수정자는 세션 아이디를 넣어줍니다.
     */

    public int inputCodeGrp(CodeGrp codeGrp) {
        return codeGrpRepository.save(codeGrp);
    }

    public List<CodeGrp> getCodeGrpList() {
        return codeGrpRepository.findByUseYn();
    }

    public int deleteCodeGrp(int id) {
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCode_grp_id(id);
        codeGrp.setUpdated_id(1); // todo 세션 아이디를 넣어준다.
        codeGrp.setUse_yn("N");
        //todo 하위 코드 삭제 처리
        //todo history 테이블에 내역 입력
        return codeGrpRepository.updateUseYnById(codeGrp);
    }
}
