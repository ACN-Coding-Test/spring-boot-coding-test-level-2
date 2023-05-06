package com.accenture.codingtest.springbootcodingtest.entity;

import com.accenture.codingtest.springbootcodingtest.util.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NOT_STARTED;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task(String title, String description, TaskStatus status, Project project, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.project = project;
        this.user = user;
    }

    public Task(String title, String description, Project project, User user) {
        this.title = title;
        this.description = description;
        this.project = project;
        this.user = user;
    }
}
