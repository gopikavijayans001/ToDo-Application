package com.project.todoapplication.service;

import com.project.todoapplication.dto.*;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<ApiResponse> saveProject(ProjectDto projectDto);

    ResponseEntity<ApiResponse> updateProject(String title, String id);

    ResponseEntity<ApiResponse> getProject(String id);

    ResponseEntity<ApiResponse> getAll(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> deleteProject(String id);

    ResponseEntity<ApiResponse> addTodo(TodoDto todoDto, String projectId);

    ResponseEntity<ApiResponse> editTodo(UpdateTodoDto todoDto, String todoId);

    ResponseEntity<ApiResponse> getAllTodoList(String projectId);

    ResponseEntity<ApiResponse> deleteTodo(String projectId, String todoId);

    ResponseEntity<ApiResponse> exportGist(ProjectSummaryDto projectSummarydto);
}
