package com.bombk1n.grinberryproject.model.dto;

import java.util.List;
import java.util.Set;

public class UserDto {

    private String username;
    private Set<String> roles;
    private List<TaskDto> tasks;

    public UserDto(String username, Set<String> roles, List<TaskDto> tasks) {
        this.username = username;
        this.roles = roles;
        this.tasks = tasks;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
