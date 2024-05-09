package com.project.todoapplication.repository;

import com.project.todoapplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByName(String username);

}
