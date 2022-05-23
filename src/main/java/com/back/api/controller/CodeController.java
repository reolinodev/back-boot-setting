package com.back.api.controller;

import com.back.api.domain.CodeEntity;
import com.back.api.domain.common.Header;
import com.back.api.service.CodeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "code controller Api")
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @ApiOperation(value = "코드 리스트를 전체 조회한다.")
    @GetMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> getCodeList(@PathVariable Integer code_grp_id, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<CodeEntity> list = codeService.getCodeList(code_grp_id);
        int listCount = list.size();

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드 그룹 값에 해당하는 코드 리스트를 전체 조회한다.")
    @GetMapping("/item/{code_grp_val}")
    public ResponseEntity<Map<String,Object>> getCodeItemList(@PathVariable String code_grp_val, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        List<CodeEntity> list = codeService.getCodeItemList(code_grp_val);
        int listCount = list.size();

        String message = listCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @ApiOperation(value = "코드를 저장한다.")
    @PostMapping("/{code_grp_id}")
    public ResponseEntity<Map<String,Object>> saveCode(
        @ApiParam(
            value = "createdRows : 생성할 코드 데이터 \n"
                + "updatedRows : 수정할 코드 데이터 \n"
                + "deletedRows : 삭제할 코드 데이터 \n"
        )
        @RequestBody CodeEntity codeEntity, @PathVariable Integer code_grp_id,
        HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        codeEntity.code_grp_id = code_grp_id;

        String message = "Code saved.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(codeEntity.created_rows.length > 0){
            int result = codeService.inputCode(codeEntity);
            if(result == 0){
                message ="No code was generated.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        if(codeEntity.updated_rows.length > 0){
            int result = codeService.updateCode(codeEntity);
            if(result == 0){
                message ="Edit failed.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        if(codeEntity.deleted_rows.length > 0){
            int result = codeService.deleteCode(codeEntity);
            if(result == 0){
                message ="Failed to delete.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
