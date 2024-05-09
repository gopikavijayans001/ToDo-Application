package com.project.todoapplication.controller;

import com.project.todoapplication.dto.*;
import com.project.todoapplication.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> editProjectTitle(@RequestParam String title, @RequestParam String id) {
        return projectService.updateProject(title, id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProjectById(@RequestParam String id) {
        return projectService.getProject(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProjectWithPagination(@RequestParam(defaultValue = "0") int pageNo, @RequestParam int pageSize) {
        return projectService.getAll(pageNo, pageSize);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProject(@RequestParam String id){
        return projectService.deleteProject(id);
    }

    @PostMapping("/todo")
    public ResponseEntity<ApiResponse> addTodo(@RequestBody TodoDto todoDto,@RequestParam String projectId) {
        return projectService.addTodo(todoDto,projectId);
    }

    @PutMapping("/todo")
    public ResponseEntity<ApiResponse> editTodo(@RequestParam UpdateTodoDto updateTodoDto, @RequestParam String projectId){
        return projectService.editTodo(updateTodoDto,projectId);
    }

    @GetMapping("/todo")
    public ResponseEntity<ApiResponse> getAllTodoList(@RequestParam String projectId){
        return projectService.getAllTodoList(projectId);
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ApiResponse> deleteTodo(@RequestParam String projectId,@RequestParam String todoId){
        return projectService.deleteTodo(projectId,todoId);
    }

    @PostMapping("/export-gist")
    public ResponseEntity<ApiResponse> exportGist(@RequestBody ProjectSummaryDto projectSummarydto) {
        return projectService.exportGist(projectSummarydto);
    }


}


