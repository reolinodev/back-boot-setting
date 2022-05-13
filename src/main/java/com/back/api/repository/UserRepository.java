package com.back.api.repository;

import com.back.api.domain.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<UserEntity> {
    int countByLoginId(String login_id);

    int updateUserPw(UserEntity userEntity);

    UserEntity findByLoginId(UserEntity userEntity);

    int countByAll(UserEntity userEntity);

    UserEntity findByUserId(int userId);

    int updateUser(UserEntity userEntity);
}
