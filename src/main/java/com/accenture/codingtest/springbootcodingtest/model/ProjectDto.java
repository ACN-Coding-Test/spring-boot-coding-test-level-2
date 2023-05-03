package com.accenture.codingtest.springbootcodingtest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Data
public class ProjectDto {
    private UUID id;
    private String name;
}
