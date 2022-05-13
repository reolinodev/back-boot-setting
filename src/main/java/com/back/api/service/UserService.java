package com.back.api.service;

import com.back.api.domain.UserAuth;
import com.back.api.domain.UserEntity;
import com.back.api.repository.UserAuthRepository;
import com.back.api.repository.UserRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    public int checkLoginId(String login_id) {
        return userRepository.countByLoginId(login_id);
    }

    /**
     * 사용자 등록
     */
    public int inputUser(UserEntity userEntity) throws NoSuchAlgorithmException {
        int result = 0;

        userEntity.setUser_pw(CryptUtils.encryptSha256(userEntity.getUser_pw()));
        userRepository.save(userEntity);

        UserEntity userResult = userRepository.findByLoginId(userEntity);
        if(userResult.getUser_id() != 0){
            UserAuth userAuth = new UserAuth();
            userAuth.setUser_id(userResult.getUser_id());
            //todo 프로퍼티로 처리 (어드민/슈퍼 관리자 권한 부여)
            userAuth.setAuth_id(1);
            result = userAuthRepository.save(userAuth);
        }
        return result;
    }

    /**
     * 사용자 비밀번호 변경
     */
    public int updateUserPw(UserEntity userEntity) throws NoSuchAlgorithmException {
        userEntity.setUser_pw(CryptUtils.encryptSha256(userEntity.getUser_pw()));
        return userRepository.updateUserPw(userEntity);
    }


    /**
     * 사용자를 전체조회(검색 및 페이징 처리).
     */
    public List<UserEntity> findAll(UserEntity userEntity) {
        userEntity.setStart_idx(userEntity.getPage_per(), userEntity.getCurrent_page());
        return userRepository.findAll(userEntity);
    }

    /**
     * 사용자를 전체조회의 토탈 카운트 조회
     */
    public int countByAll(UserEntity userEntity) {
        return userRepository.countByAll(userEntity);
    }

    /**
     * 사용자를 상세조회
     */
    public UserEntity findByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * 사용자 정보 변경
     */
    public int updateUser(UserEntity userEntity) throws NoSuchAlgorithmException {
        userEntity.setUser_pw(CryptUtils.encryptSha256(userEntity.getUser_pw()));
        return userRepository.updateUser(userEntity);
    }
}
