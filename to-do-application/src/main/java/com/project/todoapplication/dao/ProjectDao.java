package com.project.todoapplication.dao;

import com.project.todoapplication.dto.ApiResponse;
import com.project.todoapplication.model.Project;
import com.project.todoapplication.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectDao {
    Project saveProject(Project project);

    Project getProjectById(String id);

    Page<Project> getAllWithPagination(int pageNo, int pageSize);

    Project deleteProject(String id);




}
