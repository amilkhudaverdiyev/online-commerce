package com.onlinefoodcommercems.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDetail {
    private String message;
    private String status;
    private Integer statusCode;

}
