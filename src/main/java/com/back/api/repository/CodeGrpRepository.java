package com.back.api.repository;

import com.back.api.domain.CodeGrp;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGrpRepository extends CommonRepository<CodeGrp> {
    int countByCodeGrpVal(CodeGrp codeGrp);

    CodeGrp findByCodeGrpId(int codeGrpId);

    int update(CodeGrp codeGrp);
}
