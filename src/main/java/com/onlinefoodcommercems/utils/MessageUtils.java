package com.onlinefoodcommercems.utils;

import com.onlinefoodcommercems.entity.CartItem;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@NoArgsConstructor
public class MessageUtils {

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"message\":\"" + responseMessage +"\"}",httpStatus);
    }


}
