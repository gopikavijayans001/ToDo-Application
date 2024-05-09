package com.project.todoapplication.util;

import com.project.todoapplication.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  class ResponseUtil {

    public static ResponseEntity<ApiResponse> getCreatedResponse(Object response){
        ApiResponse apiResponse=ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .response(response)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> getBadRequestResponse(Object response){
        ApiResponse apiResponse=ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .response(response)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ApiResponse> getOkResponse(Object response){
        ApiResponse apiResponse=ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .response(response)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
}




