package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuth {

    public int user_id; //사용자 아이디

    public int auth_id; //권한 아이디

    public String created_at; //생성시간

    public String updated_at; //수정시간

    public String updated_id; //수정자

    public String use_yn; //사용여부
}
