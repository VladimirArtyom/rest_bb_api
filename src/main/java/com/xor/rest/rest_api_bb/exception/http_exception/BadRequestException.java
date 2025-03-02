package com.xor.rest.rest_api_bb.exception.http_exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class BadRequestException extends RuntimeException{
    public BadRequestException(final String detail) {
        super(String.format("Bad request: %s", detail));
    }
}
