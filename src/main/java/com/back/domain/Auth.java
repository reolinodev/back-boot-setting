package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Auth {

    public int auth_id; //권한 아이디

    public String auth_nm; //권한 명

    public String auth_role; //권한 구분

    public int ord; //순서

    public String bigo; //비고

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public String updated_id; //수정자

    public String use_yn; //사용여부
}
