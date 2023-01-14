package com.accenture.codingtest.springbootcodingtest.entity.model;

import lombok.Data;

@Data
public class TaskDto {

    private String id;

    private String title;

    private String description;

    private String status;

    private String projectId;

    private String projectName;

    private String userId;

    private String username;
}
