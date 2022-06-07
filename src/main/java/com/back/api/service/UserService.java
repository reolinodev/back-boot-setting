package com.back.api.service;

import com.back.api.domain.UserAuthEntity;
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

    /**
     * 아이디 중복 체크
     */
    public int checkLoginId(String loginId) {
        return userRepository.countByLoginId(loginId);
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
            UserAuthEntity userAuthEntity = new UserAuthEntity();
            userAuthEntity.user_id = userResult.user_id;
            // 어드민/슈퍼 관리자 권한 부여)
            userAuthEntity.auth_id = 1;
            result = userAuthRepository.save(userAuthEntity);
        }
        return result;
    }

    /**
     * 사용자 비밀번호 변경
     */
    public int updateUserPw(UserEntity userEntity) throws NoSuchAlgorithmException {
        userEntity.user_pw = CryptUtils.encryptSha256(userEntity.getUser_pw());
        return userRepository.updateUserPw(userEntity);
    }

    /**
     * 사용자를 전체조회(검색 및 페이징 처리).
     */
    public List<UserEntity> getUserList(UserEntity userEntity) {
        userEntity.setStart_idx(userEntity.getPage_per(), userEntity.getCurrent_page());
        return userRepository.findAll(userEntity);
    }

    /**
     * 사용자를 전체조회의 토탈 카운트 조회
     */
    public int getUserCount(UserEntity userEntity) {
        return userRepository.countByAll(userEntity);
    }

    /**
     * 사용자를 상세조회
     */
    public UserEntity getUserInfo(int userId) {
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
