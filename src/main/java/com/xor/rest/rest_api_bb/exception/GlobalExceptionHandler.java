package com.xor.rest.rest_api_bb.exception;

import com.xor.rest.rest_api_bb.exception.error_information.ClientErrorResponse;
import com.xor.rest.rest_api_bb.exception.error_information.ErrorResponse;
import com.xor.rest.rest_api_bb.exception.http_exception.BadRequestException;
import com.xor.rest.rest_api_bb.exception.http_exception.InternalServerErrorException;
import com.xor.rest.rest_api_bb.exception.http_exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> resourceNotFoundErrorHandler(ResourceNotFoundException e, HttpServletRequest req) {
        String uri = req.getRequestURI();
        ErrorResponse clientError = new ClientErrorResponse(
                e.getMessage(),
                LocalDateTime.now(),
                uri,
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(clientError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ErrorResponse> badRequestErrorHandler(BadRequestException e, HttpServletRequest req) {
        String uri = req.getRequestURI();
        ErrorResponse clientError = new ClientErrorResponse(
                e.getMessage(),
                LocalDateTime.now(),
                uri,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(clientError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InternalServerErrorException.class)
    ResponseEntity<ErrorResponse> internalServerErrorErrorHandler(InternalServerErrorException e, HttpServletRequest req) {
        String uri = req.getRequestURI();
        ErrorResponse clientError = new ClientErrorResponse(
                e.getMessage(),
                LocalDateTime.now(),
                uri,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(clientError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception e, HttpServletRequest req) {
        String uri = req.getRequestURI();
        ErrorResponse clientError = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now(),
                uri,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<ErrorResponse>(clientError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
