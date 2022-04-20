package com.back.api.controller;

import com.back.api.domain.Code;
import com.back.api.domain.common.Header;
import com.back.api.domain.common.ValidationGroups;
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
    @PutMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> inputCode(
        @ApiParam(
            value = "code_nm : 코드 명, 필수값, 10자 \n"
                + "code_val : 코드 값, 필수값, 10자 \n"
                + "bigo : 비고, 165자 \n"
                + "ord : 코드 순서, 10자 \n"
        )
        @Validated(ValidationGroups.CodeGroup1.class) @RequestBody Code param,
        @PathVariable Integer code_grp_id,
        HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        param.setCode_grp_id(code_grp_id);
        int result = codeService.inputCode(param);

        String message = "코드가 생성 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(result == 0){
            message ="정상적으로 생성이 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else if(result == -1) {
            message ="중복된 값이 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }


    @ApiOperation(value = "사용가능한 코드 리스트를 전체 조회한다.")
    @GetMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> getCodeList(@PathVariable Integer code_grp_id, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<Code> list = codeService.getCodeList(code_grp_id);
        int listCount = list.size();

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @ApiOperation(value = "코드 데이터를 수정한다.")
    @PutMapping("/update/{code_id}")
    public ResponseEntity<Map<String,Object>> updateCode(
        @ApiParam(
            value = "code_nm : 코드 명, 필수값, 10자 \n"
                + "bigo : 비고, 165자 \n"
                + "ord : 코드 순서, 10자 \n"
        )
        @Validated(ValidationGroups.CodeGroup2.class) @RequestBody Code param,
        @PathVariable Integer code_id,
        HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        param.setCode_id(code_id);
        int result = codeService.updateCode(param);

        String message = "코드가 수정 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="정상적으로 수정 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @ApiOperation(value = "코드를 삭제한다. 실제 삭제하지는 않고 사용여부를 변경한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_id", value = "코드 아이디", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @DeleteMapping("/{code_id}")
    public ResponseEntity<Map<String,Object>> deleteCode(@PathVariable Integer code_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        int result = codeService.deleteCode(code_id);
        String message = "사용자가 삭제 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(result < 1){
            message ="정상적으로 삭제 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

}
