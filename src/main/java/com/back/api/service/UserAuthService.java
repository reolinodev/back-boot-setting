package com.back.api.service;

import com.back.api.domain.UserAuthEntity;
import com.back.api.repository.UserAuthRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;


    /**
     * 권한을 적용할 사용자를 조회합니다.
     */
    public List<UserAuthEntity> getInputAuthUserList(UserAuthEntity userAuthEntity) {
        userAuthEntity.setStart_idx(userAuthEntity.getPage_per(), userAuthEntity.getCurrent_page());
        return userAuthRepository.findByAuthIdNotAndUseYn(userAuthEntity);
    }

    /**
     * 권한을 적용할 사용자의 총 수를 조회합니다.
     */
    public int getInputAuthUserCount(UserAuthEntity userAuthEntity) {
        return userAuthRepository.countByAuthIdNotAndUseYn(userAuthEntity);
    }

    /**
     * 사용자의 권한을 등록합니다.
     */
    public int inputUserAuth(UserAuthEntity userAuthEntity) {
        int result = 0;
        int[] arr = userAuthEntity.user_arr;
        for (int j : arr) {
            userAuthEntity.user_id = j;
            result = userAuthRepository.save(userAuthEntity);
        }

        return result;
    }

    /**
     *  사용자의 권한을 조회합니다.
     */
    public List<UserAuthEntity> getAuthUserList(UserAuthEntity userAuthEntity) {
        userAuthEntity.setStart_idx(userAuthEntity.getPage_per(), userAuthEntity.getCurrent_page());
        return userAuthRepository.findAll(userAuthEntity);
    }

    /**
     *  사용자의 권한 리스트의 카운트를 조회합니다.
     */
    public int getAuthUserCount(UserAuthEntity userAuthEntity) {
        return userAuthRepository.countAll(userAuthEntity);
    }


    /**
     * 사용자의 권한을 삭제합니다.
     */
    public int deleteUserAuth(UserAuthEntity userAuthEntity) {
        return userAuthRepository.deleteUserAuth(userAuthEntity);
    }

}
