package com.back.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
    String statusCode;
    String requestUrl;
    String message;
    String resultCode;

    List<Error> errorList;
}
