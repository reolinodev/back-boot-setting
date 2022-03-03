package com.back.api.domain.common;

import java.util.List;

import com.back.api.domain.common.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    String requestUrl;
    String message;
    String resultCode;

    List<Error> errorList;
}