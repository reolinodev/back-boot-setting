package com.back.api.controller;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "user controller Api")
@RequestMapping("/api")
public class UserController {

    @Autowired
	UserService userService;

    @ApiOperation(value = "사용자를 전체 조회한다.")
    @PostMapping("/user")
    public ResponseEntity <ArrayList<User>> findAllUser(
        @ApiParam(
            value = "name : 이름 , 널허용 \n" 
                +   "email : 이메일 ,널허용",    
            example = "{\n  name:이름,\n  email:이메일\n}")
        @RequestBody HashMap<String, Object> params){
        return new ResponseEntity <ArrayList<User>>(userService.findAll(params), HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 상세 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
    @GetMapping("/user/{id}")
	public ResponseEntity <User> findUser(@PathVariable String id) {
        return new ResponseEntity <User>(userService.findById(id), HttpStatus.OK);
	}


    @ApiOperation(value = "사용자를 입력한다.")
    @PutMapping("/user")
    public ResponseEntity<?> user(
        @ApiParam(
            value = "name : 이름, 필수값, 2~10자 \n"
                +"email : 이메일, 필수값, 이메일형식 제한 \n" 
                +"birth : 생년월일, 널허용, 8자리(19000101) 형식 \n" 
                +"password : 비밀번호, 필수값, 최대 20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n" 
                +"phone : 휴대폰, 필수값, 휴대폰번호형식 제한" 
        )
        @Valid @RequestBody User user){
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @ApiOperation(value = "사용자를 수정한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
    @PutMapping("/user/{id}")
    public ResponseEntity<HashMap<String, Object>> updateUser(
        @Valid @RequestBody User user, 
        @PathVariable String id) {
        
        HashMap<String, Object> resultMap = ReturnValue.getResultStatus(
            userService.update(user), 
            "수정에 성공했습니다.", 
            "수정에 실패했습니다."
        );

        return new ResponseEntity<HashMap<String, Object>> (resultMap, HttpStatus.OK);
    }


    @ApiOperation(value = "사용자를 삭제한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
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
