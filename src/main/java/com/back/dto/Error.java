package com.back.dto;

import lombok.Data;

@Data
public class Error {
    private String field;
    private String message;
    private String invalidValue;
}
