package com.back.api.repository;

import com.back.api.domain.Code;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CommonRepository<Code> {
    int countByCodeGrpIdAndCodeVal(Code code);

    List<Code> findByUseYn(int id);

    int update(Code code);
}
