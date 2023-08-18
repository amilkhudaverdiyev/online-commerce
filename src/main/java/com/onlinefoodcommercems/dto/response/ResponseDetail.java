package com.onlinefoodcommercems.dto.response;

import lombok.*;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDetail {
    private String message;
    private String status;
    private Integer statusCode;

}
