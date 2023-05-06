package com.accenture.codingtest.springbootcodingtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private UUID id;
    @NotBlank(message = "project name should be required")
    private String name;

    public ProjectDTO(String name) {
        this.name = name;
    }
}
