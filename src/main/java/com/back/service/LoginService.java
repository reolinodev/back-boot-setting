package com.back.service;

import com.back.api.domain.User;
import com.back.repository.LoginRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public int checkLoginId(User user) {
        return loginRepository.countByLoginId(user);
    }

    public int checkUserPw(User user) throws NoSuchAlgorithmException {
        user.setUser_pw(CryptUtils.encryptSha256(user.getUser_pw()));

        return loginRepository.countByLoginIdAndUserPw(user);
    }

}
