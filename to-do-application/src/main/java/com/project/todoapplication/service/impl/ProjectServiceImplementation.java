package com.project.todoapplication.service.impl;

import com.project.todoapplication.dao.ProjectDao;
import com.project.todoapplication.dto.*;
import com.project.todoapplication.model.Project;
import com.project.todoapplication.model.Todo;
import com.project.todoapplication.service.ProjectService;
import com.project.todoapplication.util.CommonUtils;
import com.project.todoapplication.util.GitHubGistUtil;
import com.project.todoapplication.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {
    private final ProjectDao projectDao;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> saveProject(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        project.setId(UUID.randomUUID().toString());
        project.setCreatedOn(CommonUtils.getCurrentTime());
        return ResponseUtil.getCreatedResponse(projectDao.saveProject(project));
    }

    @Override
    public ResponseEntity<ApiResponse> updateProject(String title, String id) {
        Project project = projectDao.getProjectById(id);
        if (!Objects.isNull(project)) {
            project.setTitle(title);
            return ResponseUtil.getOkResponse(projectDao.saveProject(project));
        }
        return ResponseUtil.getBadRequestResponse("Project Id not found");
    }

    @Override
    public ResponseEntity<ApiResponse> getProject(String id) {
        Project project = projectDao.getProjectById(id);
        if (!Objects.isNull(project)) {
            return ResponseUtil.getOkResponse(project);
        }
        return ResponseUtil.getBadRequestResponse("Project Id not found");
    }

    @Override
    public ResponseEntity<ApiResponse> getAll(int pageNo, int pageSize) {
        Page<Project> projectList = projectDao.getAllWithPagination(pageNo, pageSize);
        return ResponseUtil.getOkResponse(projectList);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProject(String id) {
        Project project = projectDao.deleteProject(id);
        if (Objects.isNull(project)) {
            return ResponseUtil.getBadRequestResponse("Project Id not found");
        }
        return ResponseUtil.getOkResponse("Project Removed ");
    }

    @Override
    public ResponseEntity<ApiResponse> addTodo(TodoDto todoDto, String projectId) {
        Project project = projectDao.getProjectById(projectId);
        if (Objects.isNull(projectId)) {
            return ResponseUtil.getBadRequestResponse("Project Id not found");
        }
        Todo todo = modelMapper.map(todoDto, Todo.class);
        todo.setId(UUID.randomUUID().toString());
        todo.setCreatedDate(CommonUtils.getCurrentTime());
        todo.setUpdateDate(CommonUtils.getCurrentTime());
        todo.setStatus("Pending");
        List<Todo> todoList = project.getTodoList();
        todoList = Objects.nonNull(todoList) ? todoList : new ArrayList<>();
        todoList.add(todo);
        project.setTodoList(todoList);
        return ResponseUtil.getCreatedResponse(projectDao.saveProject(project));
    }

    @Override
    public ResponseEntity<ApiResponse> editTodo(UpdateTodoDto todoDto, String projectId) {
        Project project = projectDao.getProjectById(projectId);
        if (Objects.isNull(projectId)) {
            return ResponseUtil.getBadRequestResponse("Project Id not found");
        }
        project.getTodoList().stream()
                .filter(todo -> todo.getId().equals(todoDto.getId()))
                .findFirst()
                .ifPresent(todo -> {
//                    todo.setTitle(updatedTodo.getTitle());
//                    todo.setDescription(updatedTodo.getDescription());
//                    todo.setCompleted(updatedTodo.isCompleted());
                    modelMapper.map(todoDto, Todo.class);
                    todo.setUpdateDate(CommonUtils.getCurrentTime());
                    projectDao.saveProject(project);
                });
        return ResponseUtil.getOkResponse(project);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllTodoList(String projectId) {
        Project project = projectDao.getProjectById(projectId);
        if (Objects.isNull(projectId)) {
            return ResponseUtil.getBadRequestResponse("Project Id not found");
        }
        List<Todo> todoList = project.getTodoList();
        return ResponseUtil.getOkResponse(todoList);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteTodo(String projectId, String todoId) {
        Project project = projectDao.getProjectById(projectId);
        if (Objects.isNull(projectId)) {
            return ResponseUtil.getBadRequestResponse("Project Id not found");
        }
        project.getTodoList().stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .ifPresent(todo -> {
                    project.getTodoList().remove(todo);
                });
        return ResponseUtil.getOkResponse(projectDao.saveProject(project));
    }

    @Override
    public ResponseEntity<ApiResponse> exportGist(ProjectSummaryDto projectSummarydto) {
        String markdownContent = GitHubGistUtil.generateMarkdownContent(projectSummarydto.getTitle(), projectSummarydto.getCompletedTodos(), projectSummarydto.getTotalTodos(), projectSummarydto.getPendingTodos(), projectSummarydto.getCompletedTodosList());
        String exportSummary = GitHubGistUtil.createGist(markdownContent, projectSummarydto.getGithubToken());
        return ResponseUtil.getOkResponse(exportSummary);

    }


}
