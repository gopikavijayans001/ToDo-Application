package com.project.todoapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "project")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private String id;
    private String title;
    private String CreatedOn;
    private List<Todo> todoList;
}
