package com.project.todoapplication.dao.impl;

import com.project.todoapplication.dao.ProjectDao;
import com.project.todoapplication.model.Project;
import com.project.todoapplication.model.Todo;
import com.project.todoapplication.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectDaoImplementation implements ProjectDao {
    private final MongoTemplate mongoTemplate;
    private final ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(String id) {
        Optional<Project> option = projectRepository.findById(id);
        return option.orElse(null);
    }

    @Override
    public Page<Project> getAllWithPagination(int pageNo, int pageSize) {
        return projectRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    @Override
    public Project deleteProject(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, Project.class);
    }


    }



