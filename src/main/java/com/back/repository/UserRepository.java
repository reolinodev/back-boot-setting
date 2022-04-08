package com.back.repository;

import com.back.api.domain.UserSample;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<UserSample> {
    int update(UserSample user); //사용자 업데이트
}
