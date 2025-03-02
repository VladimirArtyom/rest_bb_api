package com.xor.rest.rest_api_bb.payload.response;


import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class ApiResponse<T> {
    private final String message;
    private final int statusCode;
    private final Map<String, T> data;

    public ApiResponse(String message, int statusCode, String key, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = Collections.singletonMap(key, data);
    }

}
