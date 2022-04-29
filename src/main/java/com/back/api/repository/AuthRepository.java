package com.back.api.repository;

import com.back.api.domain.Auth;
import com.back.api.domain.CodeGrp;
import com.back.api.domain.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CommonRepository<Auth> {

    List<Auth> findByUseYn();
}
