package com.back.library;

import javax.servlet.http.HttpServletRequest;
import com.back.dto.Header;

public class ResForm {

    private ResForm() {
        throw new IllegalStateException("ResForm class");
    }

    public static Header setHeader(String message, String code,  HttpServletRequest httpServletRequest){
        Header header = new Header();
        header.setMessage(message);
        header.setRequestUrl(httpServletRequest.getRequestURI());
        header.setResultCode(code);
        return header;
    }
}
