package com.project.todoapplication.service;

import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ApiResponse> saveUser(UserDto userDto);

    ResponseEntity<ApiResponse> loginUser(String userName, String password );
}
