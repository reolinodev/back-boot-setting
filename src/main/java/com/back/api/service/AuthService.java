package com.back.api.service;

import com.back.api.domain.AuthEntity;
import com.back.api.repository.AuthRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    /**
     * 권한을 전체조회 합니다.
     */
    public List<AuthEntity> getAuthList(AuthEntity authEntity) {
        authEntity.setStart_idx(authEntity.getPage_per(), authEntity.getCurrent_page());
        return authRepository.findAll(authEntity);
    }

    /**
     * 동일 권한 값을 가지고 있는지 확인 합니다.
     */
    public int checkAuthVal(AuthEntity authEntity) {
        return authRepository.countByAuthVal(authEntity);
    }

    /**
     * 권한을 등록합니다.
     */
    public int inputAuth(AuthEntity authEntity) {
        return authRepository.save(authEntity);
    }


    /**
     * 권한의 상세 정보릊 조회합니다.
     */
    public AuthEntity getAuthInfo(int auth_id) {
        return authRepository.findByAuthId(auth_id);
    }


    /**
     *  권한 정보를 수정합니다.
     */
    public int updateAuth(AuthEntity authEntity) {
        return authRepository.updateAuth(authEntity);
    }

    /**
     * 권한 구분에 속한 권한을 조회합니다.
     */
    public List<AuthEntity> getAuthRoleList(AuthEntity authEntity) {
        return authRepository.findByAuthRoleAndUseYn(authEntity);
    }


}
