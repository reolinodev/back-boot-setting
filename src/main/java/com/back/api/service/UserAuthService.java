package com.back.api.service;

import com.back.api.domain.Auth;
import com.back.api.domain.UserAuth;
import com.back.api.repository.AuthRepository;
import com.back.api.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;


    public int inputUserAuth(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }



//    /**
//     * 사용자를 전체조회 합니다.
//     */
//    public List<UserSample> findAll(UserSample user) {
//        return userRepository.findAll(user);
//    }
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
