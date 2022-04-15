package com.back.api.controller;

import com.back.api.domain.Code;
import com.back.api.domain.CodeGrp;
import com.back.api.domain.common.Header;
import com.back.api.domain.common.ValidationGroups;
import com.back.api.service.CodeGrpService;
import com.back.api.service.CodeService;
import com.back.support.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "code controller Api")
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @ApiOperation(value = "코드를 입력한다.")
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> inputCode(
        @ApiParam(
            value = "code_grp_id : 코드 그룹 아이디, 필수값 \n"
                    + "code_nm : 코드 명, 필수값, 15자 \n"
                    + "code_val : 코드 값, 필수값, 10자 \n"
                    + "bigo : 비고 \n"
                    + "ord : 순서 \n"
        )
        @Validated(ValidationGroups.CodeAllGroup.class) @RequestBody Code param, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        int result = codeService.inputCode(param);

        String message = "코드가 생성 되었습니다.";
        String code = "ok";

        if(result == 0){
            message ="정상적으로 생성이 되지 않았습니다.";
            code = "fail";
        }else if(result == -1) {
            message ="중복된 값이 존재합니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }



}
