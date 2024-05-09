package com.project.todoapplication.repository;

import com.project.todoapplication.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project,String> {
}
