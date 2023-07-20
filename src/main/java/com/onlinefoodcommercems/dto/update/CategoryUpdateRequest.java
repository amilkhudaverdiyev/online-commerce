package com.onlinefoodcommercems.dto.update;

import lombok.Builder;

@Builder
public record CategoryUpdateRequest (
         String name
){
}
