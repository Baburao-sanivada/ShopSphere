package com.backend.ShopSphere.DTO;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class SignUpDto {
    private String username;
    @NotBlank
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
