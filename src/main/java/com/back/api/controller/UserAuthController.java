package com.back.api.controller;

import com.back.api.domain.UserAuthEntity;
import com.back.api.domain.common.Header;
import com.back.api.service.UserAuthService;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "user auth controller Api")
@RequestMapping("/api/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @ApiOperation(value = "권한을 입력할 대상을 전체 조회한다.")
    @PostMapping("/user")
    public ResponseEntity<Map<String,Object>> getInputAuthUserList(
        @ApiParam(
            value = "auth_id : 권한 아이디 , 필수 \n"
                +   "search_str : 검색어 ,널허용\n"
                +   "page_per : 페이지당 항목수 ,필수\n"
                +   "current_page : 현재 페이지, 필수\n")
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<UserAuthEntity> list = userAuthService.getInputAuthUserList(userAuthEntity);
        int listCount = userAuthService.getInputAuthUserCount(userAuthEntity);

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);


        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 권한을 입력한다.")
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> inputUserAuth(
        @ApiParam(
            value = "auth_id : 권한 아이디, 필수값 \n"
                +"user_arr : 사용자 아이디 array, 필수값 \n"
        )
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        String message = "Auth has been created.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int result = userAuthService.inputUserAuth(userAuthEntity);

        if(result < 1){
            message ="Auth creation failed.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "사용자의 권한을 전체 조회한다.")
    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> getAuthUserList(
        @ApiParam(
            value = "auth_id : 권한 아이디 , 널허용 \n"
                +   "auth_role : 권한 구분 ,널허용\n"
                +   "search_str : 검색어 ,널허용\n"
                +   "page_per : 페이지당 항목수 ,필수\n"
                +   "current_page : 현재 페이지, 필수\n")
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<UserAuthEntity> list = userAuthService.getAuthUserList(userAuthEntity);
        int listCount = userAuthService.getAuthUserCount(userAuthEntity);

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);
        responseMap.put("total", listCount);


        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 권한을 삭제한다.")
    @DeleteMapping("/")
    public ResponseEntity<Map<String,Object>> deleteUserAuth(
        @RequestBody UserAuthEntity userAuthEntity, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        int result = userAuthService.deleteUserAuth(userAuthEntity);

        String message = "The user's authority has been deleted.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="authority deletion failed.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
