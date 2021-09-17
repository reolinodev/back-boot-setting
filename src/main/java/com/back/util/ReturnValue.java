package com.back.util;

import java.util.HashMap;

public class ReturnValue {
    /* 
    함수명 : getResultStatus
    내용: 입력, 수정, 삭제의 결과값을 받아서 HashMap 형태로 리턴을 해준다.
    파라미터: 결과값, 성공메시지, 실패메시지   
    */
    public static HashMap<String, Object> getResultStatus(int result, String successMsg, String failMsg){
    
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        if(result > 0){
            resultMap.put("result","success");
            resultMap.put("msg",successMsg);
        }else{
            resultMap.put("result","fail");
            resultMap.put("msg",failMsg);
        }

        return resultMap;
    }
}
