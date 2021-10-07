package com.back.dto;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    String statusCode;
    String requestUrl;
    String message;
    String resultCode;

    List<HashMap<String, Object>> data;
}
