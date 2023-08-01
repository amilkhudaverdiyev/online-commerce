package com.onlinefoodcommercems.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDetails {
    private boolean error;
    private String filename;
    private String link;
}
