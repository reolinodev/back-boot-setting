package com.back.api.repository;

import com.back.api.domain.Code;
import com.back.api.domain.CodeEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CommonRepository<CodeEntity> {

    List<CodeEntity> findByCodeGrpId(int CodeGrpId);

    List<CodeEntity> findByCodeGrpVal(String CodeGrpVal);

    int save(Code code);

    int update(Code code);
}
