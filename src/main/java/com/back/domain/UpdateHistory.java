package com.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateHistory {

    public int user_id; // 사용자 아이디

    public String api; // api

    public String created_at; //생성시간
    
}
