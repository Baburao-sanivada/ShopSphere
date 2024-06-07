package com.backend.ShopSphere.SignIn;


import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Users")
public class User {

    private String username;
    @Id
    private String email;
    private String password;

    public void setCartData(int[] cartData) {
        this.cartData = cartData;
    }

    private int[] cartData;

    public void addItemToCart(int index) {
        this.cartData[index]+=1;
    }

    private Date date;

    public int[] getCartData() {
        return cartData;
    }

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

    public User( String name, String email, String password) {
        this.username = name;
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

    public void removeItemFromCart(Integer index) {
        this.cartData[index]-=1;
    }
}
