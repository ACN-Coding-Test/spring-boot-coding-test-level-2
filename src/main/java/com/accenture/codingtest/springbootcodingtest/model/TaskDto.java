package com.accenture.codingtest.springbootcodingtest.model;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private String status;
    private UUID projectId;
    private UUID userId;
}
