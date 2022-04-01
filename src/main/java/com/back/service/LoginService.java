package com.back.service;

import com.back.domain.User;
import com.back.repository.LoginRepository;
import com.back.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    /**
     * 아이디 존재 여부를 조회합니다.
     */
    public int countByLoginId(User user) {
        return loginRepository.countByLoginId(user);
    }


//    /**
//     * 사용자를 상세 조회 합니다.
//     */
//    public Optional<User> findById(int id) {
//        return userRepository.findById(id);
//    }
//
//
//    /**
//     * 사용자를 생성합니다.
//     */
//    public int save(User user) {
//        return userRepository.save(user);
//    }
//
//
//    /**
//     * 사용자 정보를 수정합니다.
//     */
//    public int update(User user) {
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
