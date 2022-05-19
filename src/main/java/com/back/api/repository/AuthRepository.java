package com.back.api.repository;

import com.back.api.domain.AuthEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CommonRepository<AuthEntity> {

    List<AuthEntity> findByUseYn();

    int updateAuth(AuthEntity authEntity);

    AuthEntity findByAuthId(int authId);

    int countByAuthVal(AuthEntity authEntity);

    List<AuthEntity> findByAuthRoleAndUseYn(AuthEntity authEntity);
}
