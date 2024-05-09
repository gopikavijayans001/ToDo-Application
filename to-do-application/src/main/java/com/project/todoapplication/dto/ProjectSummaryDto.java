package com.project.todoapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSummaryDto {
    private String title;
    private int completedTodos;
    private int totalTodos;
    private String[] pendingTodos;
    private String[] completedTodosList;
    private String githubToken;
}
