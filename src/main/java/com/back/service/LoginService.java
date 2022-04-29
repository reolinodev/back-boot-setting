package com.back.service;

import com.back.api.domain.LoginEntity;
import com.back.repository.LoginRepository;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public int checkLoginId(LoginEntity loginEntity) {
        return loginRepository.countByLoginId(loginEntity);
    }

    public int checkUserPw(LoginEntity loginEntity) throws NoSuchAlgorithmException {
        loginEntity.setUser_pw(CryptUtils.encryptSha256(loginEntity.getUser_pw()));

        return loginRepository.countByLoginIdAndUserPw(loginEntity);
    }

    public LoginEntity getLoginId(LoginEntity loginEntity) {
        return loginRepository.findByLoginId(loginEntity);
    }

    public void inputLoginHistory(LoginEntity loginEntity) {
        loginRepository.saveLoginHistory(loginEntity);
    }

    public void updateLastLoginAt(LoginEntity loginEntity) {
        loginRepository.saveLastLoginAt(loginEntity);
    }

}
