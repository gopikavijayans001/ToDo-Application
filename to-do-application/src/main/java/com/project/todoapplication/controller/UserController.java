package com.project.todoapplication.controller;

import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.dto.UserDto;
import com.project.todoapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }


    @PostMapping ("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestParam String userName,String password){
        return userService.loginUser(userName,password);
    }


}
