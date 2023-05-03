package com.accenture.codingtest.springbootcodingtest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Project {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
}
