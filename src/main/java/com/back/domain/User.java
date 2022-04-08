package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    public int user_id; //사용자 아이디

    public String login_id; //로그인 아이디

    public String email; //이메일

    public String user_nm; //사용자 이름

    public String user_pw; //사용자 패스워드

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public String last_login_at; //수정시간

    public String use_yn; //사용여부
}
