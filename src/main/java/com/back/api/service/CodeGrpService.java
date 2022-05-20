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

    public List<CodeGrp> getCodeGrpList(CodeGrp codeGrp) {
        return codeGrpRepository.findAll(codeGrp);
    }

    public int getCodeGrpCount(CodeGrp codeGrp) {
        return codeGrpRepository.countAll(codeGrp);
    }

    public int checkCodeGrpVal(CodeGrp codeGrp) {
        return codeGrpRepository.countByCodeGrpVal(codeGrp);
    }

    public int inputCodeGrp(CodeGrp codeGrp) {
        return codeGrpRepository.save(codeGrp);
    }

    public CodeGrp getCodeGrpInfo(int code_grp_id) {
        return codeGrpRepository.findByCodeGrpId(code_grp_id);
    }

    public int updateCodeGrp(CodeGrp codeGrp) {
        return codeGrpRepository.update(codeGrp);
    }
}
