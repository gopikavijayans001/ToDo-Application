package com.project.todoapplication.dao;

import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.model.User;
import org.springframework.http.ResponseEntity;

public interface UserDao {
    User saveUser(User user);

    User login(String userName);
}
