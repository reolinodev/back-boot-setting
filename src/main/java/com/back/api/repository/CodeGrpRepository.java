package com.back.api.repository;

import com.back.api.domain.CodeGrp;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGrpRepository extends CommonRepository<CodeGrp> {
    List<CodeGrp> findByUseYn();
}
