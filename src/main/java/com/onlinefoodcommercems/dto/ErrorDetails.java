package com.onlinefoodcommercems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorDetails(Date timestamp, HttpStatus status, String message, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
