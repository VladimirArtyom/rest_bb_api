package com.xor.rest.rest_api_bb.exception;

import com.xor.rest.rest_api_bb.exception.error_information.ErrorResponse;
import com.xor.rest.rest_api_bb.exception.http_exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


}
