package com.back.api.repository;

import com.back.api.domain.UserAuthEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends CommonRepository<UserAuthEntity> {

    List<UserAuthEntity> findByAuthIdNotAndUseYn(UserAuthEntity userAuthEntity);

    int countByAuthIdNotAndUseYn(UserAuthEntity userAuthEntity);

    int deleteUserAuth(UserAuthEntity userAuthEntity);

}
