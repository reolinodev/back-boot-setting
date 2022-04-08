package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodeGrp {

    public int code_grp_id; //코드 그룹 아이디

    public String code_grp_nm; //코드 그룹 명

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public String updated_id; //수정자

    public String use_yn; //사용여부
}
