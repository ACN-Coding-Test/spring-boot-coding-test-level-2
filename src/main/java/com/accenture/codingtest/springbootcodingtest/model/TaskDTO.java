package com.accenture.codingtest.springbootcodingtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private UUID id;
    @NotBlank(message = "title should be required")
    private String title;
    private String description;
    private String status;
    @NotBlank(message = "project should be required")
    private UUID projectId;
    @NotBlank(message = "user should be required")
    private UUID userId;

}
