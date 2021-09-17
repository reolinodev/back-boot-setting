package com.back.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.back.domain.User;
import com.back.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
	UserService userService;

     //사용자 전체조회
    @PostMapping("/user")
    public ArrayList<User> findAllUser(@RequestBody HashMap<String, Object> params) {
        return userService.findAll(params);
    }

    //사용자 상세조회
    @GetMapping("/user/{id}")
	public User findUser(@PathVariable String id) {
		return userService.findById(id);
	}

    //사용자 입력
    @PutMapping("/user")
	public int createUser(@RequestBody HashMap<String, Object> params) {
		return userService.create(params);
	}

    //사용자 수정
    @PutMapping("/user/{id}")
    public int updateUser(@RequestBody HashMap<String, Object> params, @PathVariable String id) {
        return userService.update(params);
    }

    //사용자 삭제
    @DeleteMapping("/user/{id}")
	public int deleteUser(@PathVariable String id) {
		return userService.delete(id);
	}
}
