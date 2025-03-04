package com.xor.rest.rest_api_bb.exception.error_information;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServerErrorResponse extends ErrorResponse {
    private String traceId;
    public ServerErrorResponse(final String technicalMessage,
                               final LocalDateTime timeStamp,
                               final String path,
                               final int status,
                               final String traceId
                               ) {
        super(technicalMessage, timeStamp, path, status);
        this.traceId = traceId;
    }
}
