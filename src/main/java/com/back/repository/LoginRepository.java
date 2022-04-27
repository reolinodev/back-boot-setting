package com.back.repository;

import com.back.api.domain.User;
import com.back.api.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CommonRepository<User> {

    int countByLoginId(User user);

    int countByLoginIdAndUserPw(User user);
}
