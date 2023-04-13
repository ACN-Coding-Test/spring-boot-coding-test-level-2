package com.accenture.codingtest.springbootcodingtest.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Task")
public class Task {
    @Id
    private String id;// | uuid | required | pk
    private String title;// | string | required
    private String description;// | string
    private String status;// | string | required
    private  String project_id;// | uuid | required
    private  String user_id;// | uuid | required
}
