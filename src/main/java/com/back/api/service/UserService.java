package com.back.api.service;

import com.back.api.domain.User;
import com.back.api.repository.UserRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public int checkLoginId(String login_id) {
        return userRepository.countByLoginId(login_id);
    }

    public int inputUser(User user) throws NoSuchAlgorithmException {
        user.setUser_pw(CryptUtils.encryptSha256(user.getUser_pw()));
        //todo 권한 등록(관리자 권한)
        return userRepository.save(user);
    }

    public int updateUserPw(User user) throws NoSuchAlgorithmException {
        user.setUser_pw(CryptUtils.encryptSha256(user.getUser_pw()));
        return userRepository.updateUserPw(user);
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
