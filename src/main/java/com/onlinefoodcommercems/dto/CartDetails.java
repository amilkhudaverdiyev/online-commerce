package com.onlinefoodcommercems.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDetails {
    private String message;
    private String status;
    private Integer statusCode;
    private String link;
}
