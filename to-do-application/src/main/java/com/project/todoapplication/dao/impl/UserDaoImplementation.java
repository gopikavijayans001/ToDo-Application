package com.project.todoapplication.dao.impl;

import com.project.todoapplication.dao.UserDao;
import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.dto.UserDto;
import com.project.todoapplication.model.User;
import com.project.todoapplication.repository.UserRepository;
import com.project.todoapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImplementation implements UserDao {
    private final UserRepository userRepository;
    private final MongoTemplate template;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String userName) {
        return userRepository.findByName(userName);
    }
}
