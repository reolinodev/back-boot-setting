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
        User user1 = new User();
        user1.setId("1");
        user1.setEmail("aaa@gmail.com");
        user1.setName("aaa");
        user1.setBirth("19000101");
        user1.setPhoneNumber("01010002000");
        result.add(user1);
        User user2 = new User();
        user2.setId("2");
        user2.setEmail("bbb@gmail.com");
        user2.setName("bbb");
        user2.setBirth("20001231");
        user2.setPhoneNumber("01020003000");
        result.add(user2);
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
