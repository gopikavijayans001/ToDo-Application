package com.project.todoapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private String id;
    private String description;
    private String status;
    private String createdDate;
    private String updateDate;
}
