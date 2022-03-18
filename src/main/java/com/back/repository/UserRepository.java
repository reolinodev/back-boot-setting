package com.back.repository;

import com.back.api.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User> {
    int update(User user); //사용자 업데이트
}
