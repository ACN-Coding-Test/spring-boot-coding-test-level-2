package com.accenture.codingtest.springbootcodingtest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="Project")
public class Project {

    @Id
    private String id;
    private String  name;
}
