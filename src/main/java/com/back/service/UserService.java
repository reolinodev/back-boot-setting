package com.back.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.back.domain.User;

import org.springframework.stereotype.Service;

@Service("com.back.service.UserService")
public class UserService {
    
    /**
	* 사용자를 전체조회 합니다.
	*/
	public ArrayList<User> findAll(HashMap<String, Object> params) {
		ArrayList<User> result = new ArrayList<User>();
        System.out.println("findAll이 실행되었습니다");
        return result;
	}

    /**
	* 사용자를 상세 조회 합니다.
	*/
    public User findById(String id) {
	    User result = new User();
        System.out.println("findById가 실행되었습니다");
        return result;
	}

    /**
	* 사용자를 생성 합니다.
	*/
    public int create(HashMap<String, Object> params) {
	    int result = 0;
        System.out.println("create가 실행되었습니다");
        return result;
	}
    
    /**
	* 사용자를 생성 합니다.
	*/
    public int update(HashMap<String, Object> params) {
	    int result = 0;
        System.out.println("update가 실행되었습니다");
        return result;
	}

    /**
	* 사용자를 생성 합니다.
	*/
    public int delete(String id) {
	    int result = 0;
        System.out.println("delete가 실행되었습니다");
        return result;
	}
}
