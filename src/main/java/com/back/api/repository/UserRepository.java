package com.back.api.repository;

import com.back.api.domain.Code;
import com.back.api.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
    int countByLoginId(String login_id);

    int updateUserPw(User user);
}
