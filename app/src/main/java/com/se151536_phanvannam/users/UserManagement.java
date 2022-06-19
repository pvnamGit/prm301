package com.se151536_phanvannam.users;

import java.io.Serializable;

public class UserManagement implements Serializable {
    private String username;
    private String password;

    public UserManagement(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserManagement() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
