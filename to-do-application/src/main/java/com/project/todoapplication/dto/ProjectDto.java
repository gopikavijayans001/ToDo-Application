package com.project.todoapplication.dto;

import com.project.todoapplication.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String title;
    private List<Todo> todoList;

}
