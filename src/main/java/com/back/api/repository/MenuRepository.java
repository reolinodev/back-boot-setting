package com.back.api.repository;

import com.back.api.domain.AuthEntity;
import com.back.api.domain.MenuEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CommonRepository<MenuEntity> {

    List<MenuEntity> findAllMenu();
//
//    int updateAuth(AuthEntity authEntity);
//
//    AuthEntity findByAuthId(int authId);
//
//    int countByAuthVal(AuthEntity authEntity);
//
//    List<AuthEntity> findByAuthRoleAndUseYn(AuthEntity authEntity);
}
