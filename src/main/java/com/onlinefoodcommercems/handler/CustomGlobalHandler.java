package com.onlinefoodcommercems.handler;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.ErrorDetails;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.exception.PasswordRequestException;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class CustomGlobalHandler {
    @ExceptionHandler(NotDataFound.class)
    public ResponseEntity<ErrorDetails> NotFoundException(NotDataFound ex, WebRequest webRequest) {
        log.error("Not Found {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(NOT_FOUND.value())
                .error(NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDetails> IllegalStateException(IllegalStateException ex, WebRequest webRequest) {
        log.error("IllegalStateException {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(UNAUTHORIZED.value())
                .error(UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordRequestException.class)
    public ResponseEntity<ErrorDetails> PasswordRequestException(PasswordRequestException ex, WebRequest webRequest) {
        log.error("PasswordRequestException {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(UNAUTHORIZED.value())
                .error(UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> UsernameNotFoundException(UsernameNotFoundException ex, WebRequest webRequest) {
        log.error("UsernameNotFoundException {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(UNAUTHORIZED.value())
                .error(UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> MethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation {}", ex.getMessage());
        List<String> errors = new LinkedList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
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

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDetails> BindException(BindException ex, WebRequest request) {
        log.error("bind exception {}", ex.getMessage());
        List<String> errors = new LinkedList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> ConstraintViolationException(ConstraintViolationException ex, WebRequest webRequest) {
        log.error("ConstraintViolationException {}", ex.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format(constraintViolation.getMessage()))
                .collect(Collectors.toList()));
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(messages.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ErrorDetails> InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex, WebRequest webRequest) {
        log.error("invalid data {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> DublicateHandler(SQLException ex, WebRequest request) {
        log.error("DublicateHandler {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(INTERNAL_SERVER_ERROR.value())
                .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ResponseMessage.SOMETHING_WENT_WRONG)
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorDetails> MessagingException(MessagingException ex, WebRequest request) {
        log.error("messaging {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(INTERNAL_SERVER_ERROR.value())
                .error(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorDetails> FileNotException(FileNotFoundException ex, WebRequest request) {
        log.error("file not  {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(FORBIDDEN.value())
                .error(FORBIDDEN.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> ForbiddenException(AccessDeniedException ex, WebRequest request) {
        log.error("AccessDeniedException {}", ex.getMessage());
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(FORBIDDEN.value())
                .error(FORBIDDEN.getReasonPhrase())
                .message(ResponseMessage.FORBIDDEN)
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, FORBIDDEN);
    }
}
