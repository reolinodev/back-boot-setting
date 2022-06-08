package com.back.repository;

import com.back.api.domain.LoginEntity;
import com.back.api.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CommonRepository<LoginEntity> {

    int countByLoginId(LoginEntity loginEntity);

    int countByLoginIdAndUserPw(LoginEntity loginEntity);

    LoginEntity findByLoginId(LoginEntity loginEntity);

    int saveLoginHistory(LoginEntity loginEntity);

    int saveLastLoginAt(LoginEntity loginEntity);
}
