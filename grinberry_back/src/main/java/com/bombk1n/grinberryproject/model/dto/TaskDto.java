package com.bombk1n.grinberryproject.model.dto;

import com.bombk1n.grinberryproject.model.entity.todo.TaskType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private TaskType status;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;

    public TaskDto(Long id, String title, String description, TaskType status, LocalDateTime createdAt, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskDto() {
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getStatus() {
        return status;
    }

    public void setStatus(TaskType status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}