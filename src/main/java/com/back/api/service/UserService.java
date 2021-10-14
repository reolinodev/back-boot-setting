package com.back.api.service;

import java.util.List;

import com.back.api.dto.User;
import com.back.repository.UserMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
	* 사용자를 전체조회 합니다.
	*/
    public List<User> findAll(User user) {
        return userMapper.findAll(user);
	}

    /**
	* 사용자를 상세 조회 합니다.
	*/
    public User findById(Integer id) {
        return  userMapper.findById(id);
	}


    /**
	* 사용자를 생성합니다.
	*/
    public int save(User user) {
        return userMapper.save(user);
	}
    

    /**
	* 사용자 정보를 수정합니다.
	*/
    public int update(User user) {
        return userMapper.update(user);
	}


    /**
	* 사용자를 삭제 합니다.
	*/
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
	}
}
