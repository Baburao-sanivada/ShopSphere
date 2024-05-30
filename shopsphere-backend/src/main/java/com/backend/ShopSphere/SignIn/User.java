package com.backend.ShopSphere.SignIn;


import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Users")
public class User {

    private String name;
    @Id
    private String email;
    private String password;

    private int[] cartData;

    private Date date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User( String name, String email, String password) {
        this.name = name;
        this.email = email.toLowerCase();
        this.password = password;
        this.cartData = new int[200];
        this.date = new Date();
    }

    public User(String email, String password) {
        this.email = email.toLowerCase();
        this.password = password;
    }

    public User() {
    }
}
