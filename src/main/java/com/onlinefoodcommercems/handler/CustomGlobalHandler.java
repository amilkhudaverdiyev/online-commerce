package com.onlinefoodcommercems.handler;
import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.ErrorDetails;
import com.onlinefoodcommercems.exception.AuthenticationException;
import com.onlinefoodcommercems.exception.NotDataFound;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.validation.FieldError;
import com.onlinefoodcommercems.exceptions.GenericException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintDefinitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class CustomGlobalHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorDetails>  NotFoundException(GenericException ex, WebRequest webRequest){
        log.error("Not Found" + ex);

        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(NOT_FOUND.value())
                .error(NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, NOT_FOUND);
    }
    @ExceptionHandler
public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation" + ex);
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(errors.toString())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
}
    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  ConstraintViolationException(ConstraintViolationException ex, WebRequest webRequest){
       log.error("ConstraintViolationException" + ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(INTERNAL_SERVER_ERROR.value())
                .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ResponseMessage.PERCENT_VALID)
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails>  customerNull(InvalidDataAccessResourceUsageException ex, WebRequest webRequest){
        System.out.println("InvalidDataAccessResourceUsageException");
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> DublicateHandler(SQLException exception,
                                                         WebRequest request) {
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(INTERNAL_SERVER_ERROR.value())
                .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ResponseMessage.SOMETHING_WENT_WRONG)
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }
//    @ExceptionHandler
//    public ResponseEntity<ErrorDetails>  customerNull(RuntimeException ex,WebRequest webRequest){
//        var errorDetails = ErrorDetails.builder()
//                .timestamp(new Date())
//                .status(BAD_REQUEST.value())
//                .error(BAD_REQUEST.getReasonPhrase())
//                .message(ex.getMessage())
//                .errorDetail(ex.getMessage())
//                .path(webRequest.getDescription(false))
//                .build();
//        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
//    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorDetails>  customerNull(Exception ex,WebRequest webRequest){
//        System.out.println("Exception");
//        var errorDetails = ErrorDetails.builder()
//                .timestamp(new Date())
//                .status(BAD_REQUEST.value())
//                .error(BAD_REQUEST.getReasonPhrase())
//                .message(ex.getMessage())
//                .errorDetail(ex.getMessage())
//                .path(webRequest.getDescription(false))
//                .build();
//        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
//    }




}
