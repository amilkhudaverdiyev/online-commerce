package com.onlinefoodcommercems.handler;


import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.ErrorDetails;
import com.onlinefoodcommercems.exception.NotDataFound;
import jakarta.validation.ConstraintDefinitionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

//@RestControllerAdvice
public class CustomGlobalHandler {

@ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNotFound(NotDataFound ex, WebRequest webRequest){
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNull(ConstraintDefinitionException ex, WebRequest webRequest){
        System.out.println("ConstraintDefinitionException");
    var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNull(InvalidDataAccessResourceUsageException ex, WebRequest webRequest){
        System.out.println("InvalidDataAccessResourceUsageException");
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .errorDetail(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> DublicateHandler(DataIntegrityViolationException exception,
                                                                WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.SOMETHING_WENT_WRONG,
                        exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNull(RuntimeException ex,WebRequest webRequest){
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNull(Exception ex,WebRequest webRequest){
        System.out.println("Exception");
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }




}
