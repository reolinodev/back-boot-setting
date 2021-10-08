package com.back.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMap {
    String requestUrl;
    String message;
    String resultCode;

    List<Map<String,Object>> dataList;
}
