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


    public int inputUserAuth(UserAuthEntity userAuthEntity) {
        int result = 0;
        int[] arr = userAuthEntity.user_arr;
        for (int j : arr) {
            userAuthEntity.user_id = j;
            result = userAuthRepository.save(userAuthEntity);
        }

        return result;
    }


//
//
//    /**
//     * 사용자를 상세 조회 합니다.
//     */
//    public Optional<UserSample> findById(int id) {
//        return userRepository.findById(id);
//    }
//
//
//    /**
//     * 사용자를 생성합니다.
//     */
//    public int save(UserSample user) {
//        return userRepository.save(user);
//    }
//
//
//    /**
//     * 사용자 정보를 수정합니다.
//     */
//    public int update(UserSample user) {
//        return userRepository.update(user);
//    }
//
//
//    /**
//     * 사용자를 삭제 합니다.
//     */
//    public int deleteById(int id) {
//        return userRepository.deleteById(id);
//    }

}
