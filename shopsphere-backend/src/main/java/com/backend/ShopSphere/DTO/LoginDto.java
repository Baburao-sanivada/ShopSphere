package com.backend.ShopSphere.DTO;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    private String email;
    private String password;

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
