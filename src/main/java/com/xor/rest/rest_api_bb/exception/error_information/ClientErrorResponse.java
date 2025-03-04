package com.xor.rest.rest_api_bb.exception.error_information;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientErrorResponse extends ErrorResponse{
    public ClientErrorResponse(final String message,
                               final LocalDateTime timeStamp,
                               final String path,
                               final int status) {
        super(message, timeStamp, path, status);
    }
}
