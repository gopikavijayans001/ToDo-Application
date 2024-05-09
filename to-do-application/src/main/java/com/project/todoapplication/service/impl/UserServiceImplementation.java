package com.project.todoapplication.service.impl;

import com.project.todoapplication.dao.UserDao;
import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.dto.UserDto;
import com.project.todoapplication.model.User;
import com.project.todoapplication.service.UserService;
import com.project.todoapplication.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> saveUser( UserDto userDto) {
       User user= modelMapper.map(userDto, User.class);
       user.setId(UUID.randomUUID().toString());
               userDao.saveUser(user);
        return ResponseUtil.getCreatedResponse(user);
    }

    @Override
    public ResponseEntity<ApiResponse> loginUser(String userName, String password) {
       User user= userDao.login(userName);
       if(Objects.nonNull(user) && user.getPassword().equals(password))
       {
           return ResponseUtil.getOkResponse(user);
       }
        return ResponseUtil.getBadRequestResponse("User not found");
    }

}
