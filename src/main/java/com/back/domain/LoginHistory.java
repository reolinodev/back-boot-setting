package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginHistory {

    public int user_id; //사용자 아이디

    public String login_device; //로그인 기기

    public String device_browser; //기기 브라우져

    public String created_at; //생성시간

}
