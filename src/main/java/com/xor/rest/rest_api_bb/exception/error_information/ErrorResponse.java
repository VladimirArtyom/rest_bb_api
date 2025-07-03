package com.xor.rest.rest_api_bb.exception.error_information;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timeStamp;
    private final int status;
    private final String path;

    public ErrorResponse(final String message,
                         final LocalDateTime timeStamp,
                         final String path,
                         final int status) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.path = path;
        this.status = status;
    }
}
