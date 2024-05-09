package com.project.todoapplication.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {
    private HttpStatus httpStatus;
    private int statusCode;
    private Object response;
}

