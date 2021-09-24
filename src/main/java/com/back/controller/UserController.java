package com.back.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import com.back.dto.User;
import com.back.service.UserService;
import com.back.util.ReturnValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <ArrayList<User>> findAllUser(@RequestBody HashMap<String, Object> params) {
        
        return new ResponseEntity <ArrayList<User>>(userService.findAll(params), HttpStatus.OK);
    }

    //사용자 상세조회
    @GetMapping("/user/{id}")
	public ResponseEntity <User> findUser(@PathVariable String id) {
        
        return new ResponseEntity <User>(userService.findById(id), HttpStatus.OK);
	}

    //사용자 입력
    @PutMapping("/user")
    public ResponseEntity<?> user(@Valid @RequestBody User user){
        
        userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    //사용자 수정
    @PutMapping("/user/{id}")
    public ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody HashMap<String, Object> params, @PathVariable String id) {
        
        HashMap<String, Object> resultMap = ReturnValue.getResultStatus(
            userService.update(params), 
            "수정에 성공했습니다.", 
            "수정에 실패했습니다."
        );

        return new ResponseEntity<HashMap<String, Object>> (resultMap, HttpStatus.OK);
    }

    //사용자 삭제
    @DeleteMapping("/user/{id}")
	public ResponseEntity<HashMap<String, Object>> deleteUser(@PathVariable String id) {
        
        HashMap<String, Object> resultMap = ReturnValue.getResultStatus(
            userService.delete(id), 
            "삭제에 성공했습니다.", 
            "삭제에 실패했습니다."
        );

        return new ResponseEntity<HashMap<String, Object>> (resultMap, HttpStatus.OK);
	}


}
