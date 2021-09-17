package com.back.domain;

import lombok.Data;

@Data
public class User {
    
    private String id;
    
    private String name;

    private String birth;

    private String password;
    
    private String phoneNumber;

    private String email;
}
