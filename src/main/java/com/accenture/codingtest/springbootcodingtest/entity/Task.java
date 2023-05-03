package com.accenture.codingtest.springbootcodingtest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private UUID projectId;
    @Column(nullable = false)
    private UUID userId;
}
