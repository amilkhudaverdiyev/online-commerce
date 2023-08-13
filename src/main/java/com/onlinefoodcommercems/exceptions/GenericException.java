package com.onlinefoodcommercems.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GenericException extends RuntimeException{
    private final String code;
    private final String message;
    private final Integer status;

    public GenericException(String message,String code, Integer status) {
       super(message);
        this.message = message;
        this.code = code;
        this.status = status;

    }



}
