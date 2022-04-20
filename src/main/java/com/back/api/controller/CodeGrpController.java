package com.back.api.controller;

import com.back.api.domain.CodeGrp;
import com.back.api.domain.common.Header;
import com.back.api.domain.common.ValidationGroups;
import com.back.api.service.CodeGrpService;
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
@Api(value = "codeGrp controller Api")
@RequestMapping("/api/codeGrp")
@RequiredArgsConstructor
public class CodeGrpController {

    private final CodeGrpService codeGrpService;

    @ApiOperation(value = "코드 그룹을 입력한다.")
    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> inputCodeGrp(
        @ApiParam(
            value = "code_grp_nm : 코드 그룹 명, 필수값, 15자 \n"
                + "code_grp_val : 코드 그룹 값, 필수값, 10자 \n"
        )
        @Validated(ValidationGroups.CodeGrpGroup1.class) @RequestBody CodeGrp codeGrp, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        int result = codeGrpService.inputCodeGrp(codeGrp);

        String message = "코드 그룹이 생성 되었습니다.";
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

    @ApiOperation(value = "사용가능한 코드 그룹 리스트를 전체 조회한다.")
    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getCodeGrpList(HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<CodeGrp> list = codeGrpService.getCodeGrpList();
        int listCount = list.size();

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드 그룹 데이터를 수정한다.")
    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> updateCodeGrp(
        @ApiParam(
            value = "code_grp_id : 아이디 \n"
                + "code_grp_nm : 그룹 코드 명, 필수값, 15자\n"
        )
        @Validated(ValidationGroups.CodeGrpGroup2.class) @RequestBody CodeGrp codeGrp, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        int result = codeGrpService.updateCodeGrp(codeGrp);

        String message = "코드 그룹이 수정 되었습니다.";
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


    @ApiOperation(value = "코드 그룹을 삭제한다. 실제 삭제하지는 않고 사용여부 변경하며 하위 코드들 역시 사용여부 변경 처리 한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_grp_id", value = "코드 그룹 아이디", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @DeleteMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> deleteCodeGrp(@PathVariable Integer code_grp_id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        int result = codeGrpService.deleteCodeGrp(code_grp_id);
        String message = "코드 그룹이 삭제 되었습니다.";
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
