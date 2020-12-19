package com.nguyentai.firebasedb.entities;

public class User {
    private String userId;
    private String displayName;
    private String password;

    public User() {
    }

    public User(String userId, String displayName, String password) {
        this.userId = userId;
        this.displayName = displayName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
