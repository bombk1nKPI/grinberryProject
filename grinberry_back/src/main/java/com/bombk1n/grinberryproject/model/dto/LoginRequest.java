package com.bombk1n.grinberryproject.model.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    // Getters and Setters
}
