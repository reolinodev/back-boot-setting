package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    public int id; //사용자 아이디

    public String login_id; //로그인 아이디

    public String user_nm; //사용자 이름

    public String user_pw; //사용자 패스워드

    public String email; //이메일

    public String use_yn; //사용여부

    public String use_yn_nm; //사용여부명

    public String auth_id; //권한 아이디

    public String auth_nm; //권한명

    public String auth_role; //권한 영역

    public String auth_role_nm; //권한 영역명

    public int ord; //권한 우선순위

    public String bigo; //비고

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public int updated_id; //수정자
}
