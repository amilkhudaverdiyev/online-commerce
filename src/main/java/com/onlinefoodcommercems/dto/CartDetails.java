package com.onlinefoodcommercems.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class CartDetails {
    private ResponseEntity<String> message;
    private String link;
}
