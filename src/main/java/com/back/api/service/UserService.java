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
