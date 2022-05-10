package com.back.api.controller;

import com.back.api.domain.User;
import com.back.api.domain.UserEntity;
import com.back.api.domain.common.Header;
import com.back.api.domain.common.ValidationGroups;
import com.back.api.service.UserService;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "auth controller Api")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @ApiOperation(value = "아이디가 사용 가능한지 체크한다.")
    @GetMapping("/{login_id}")
    public ResponseEntity<Map<String,Object>> checkLoginId(@PathVariable String login_id, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        int count = userService.checkLoginId(login_id);

        String message = "ID is available";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(count > 0){
            message = "The ID that already exists.";
            code ="fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<> (responseMap, status);
    }

    @ApiOperation(value = "사용자를 입력한다.")
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> save(
        @ApiParam(
            value = "login_id : 아이디, 필수값, 50자 \n"
                +"user_nm : 이름, 필수값, 15자  \n"
                +"email : 이메일, 필수값, 이메일형식 제한 \n"
                +"cell_phone : 휴대폰, 필수값, 휴대폰번호형식 제한\n"
                +"user_pw : 비밀번호, 필수값, 8~20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n"
        )
        @Validated(ValidationGroups.UserGroup1.class) @RequestBody UserEntity userEntity
        , HttpServletRequest httpServletRequest)
        throws NoSuchAlgorithmException {
        Map <String,Object> responseMap = new HashMap<>();

        int result = userService.inputUser(userEntity);

        String message = "User has been created.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result < 1){
            message ="User creation failed.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "사용자의 비밀번호를 수정한다.")
    @PutMapping("/userPw")
    public ResponseEntity<Map<String,Object>> updateUserPw(
        @ApiParam(
            value = "login_id : 아이디, 필수값, 50자 \n"
                +"user_pw : 비밀번호, 필수값, 8~20자, 비밀번호형식(영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자) \n"
        )
        @Validated(ValidationGroups.UserGroup2.class) @RequestBody UserEntity userEntity
        , HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {

        Map <String,Object> responseMap = new HashMap<>();
        String loginId = userEntity.getLogin_id();
        int count = userService.checkLoginId(loginId);

        String message = "Your password has been changed.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(count == 0){
            message = "ID does not exist.";
            code ="fail";
            status = HttpStatus.BAD_REQUEST;
        }else {
            int result = userService.updateUserPw(userEntity);
            if(result < 1){
                message = "Edit failed.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

//
//
//
//
//
//
//    @ApiOperation(value = "사용자를 전체 조회한다.")
//    @PostMapping("/")
//    public ResponseEntity<Map<String,Object>> findAll(
//            @ApiParam(
//                    value = "name : 이름 , 널허용 \n"
//                            +   "email : 이메일 ,널허용",
//                    example = "{\n  name:이름,\n  email:이메일\n}")
//            @RequestBody UserSample user, HttpServletRequest httpServletRequest){
//        Map <String,Object> responseMap = new HashMap<>();
//
//        List<UserSample> list = userService.findAll(user);
//        int listCount = list.size();
//
//        String message = listCount+"건이 조회되었습니다.";
//        String code = "ok";
//        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
//
//        responseMap.put("header", header);
//        responseMap.put("data", list);
//
//        return new ResponseEntity<> (responseMap, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "사용자를 상세 조회한다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity <Map<String,Object>> findById(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
//        Map <String,Object> responseMap = new HashMap<>();
//        Optional<UserSample> data = userService.findById(id);
//
//        String message = "1건이 조회되었습니다.";
//        String code = "ok";
//        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
//
//        responseMap.put("header", header);
//        responseMap.put("data", data);
//
//        return new ResponseEntity<> (responseMap, HttpStatus.OK);
//    }
//
//
//
//
//
//    @ApiOperation(value = "사용자를 수정한다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String,Object>> updateUser(
//            @Valid @RequestBody UserSample user,
//            @PathVariable Integer id, HttpServletRequest httpServletRequest) {
//
//        Map <String,Object> responseMap = new HashMap<>();
//
//        user.setId(id);
//        int result = userService.update(user);
//        String message = "사용자 정보가 수정이 되었습니다.";
//        String code = "ok";
//        if(result < 1){
//            message ="정상적으로 수정이 되지 않았습니다.";
//            code = "fail";
//        }
//
//        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
//        responseMap.put("header", header);
//
//        return new ResponseEntity<>(responseMap, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "사용자를 삭제한다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "사용자 고유키", required = true, dataType = "Integer", paramType = "path", example = "1"),
//    })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
//        Map <String,Object> responseMap = new HashMap<>();
//
//        int result = userService.deleteById(id);
//        String message = "사용자가 삭제 되었습니다.";
//        String code = "ok";
//        if(result < 1){
//            message ="정상적으로 삭제 되지 않았습니다.";
//            code = "fail";
//        }
//
//        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
//        responseMap.put("header", header);
//
//        return new ResponseEntity<>(responseMap, HttpStatus.OK);
//    }
}
