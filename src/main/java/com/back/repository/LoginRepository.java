package com.back.repository;

import com.back.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository  {
    int countByLoginId(User user); //사용자 업데이트
}
