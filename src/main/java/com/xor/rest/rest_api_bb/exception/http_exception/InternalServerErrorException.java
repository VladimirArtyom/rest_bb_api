package com.xor.rest.rest_api_bb.exception.http_exception;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(final String detailError) {
        super(String.format("Internal Server Error: %s", detailError));
    }
}
