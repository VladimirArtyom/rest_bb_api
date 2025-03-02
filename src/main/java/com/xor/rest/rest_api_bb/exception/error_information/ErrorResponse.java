package com.xor.rest.rest_api_bb.exception.error_information;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class ErrorResponse {
    private String message;
    private LocalDateTime timeStamp;

    public ErrorResponse(final String message, final LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

}
