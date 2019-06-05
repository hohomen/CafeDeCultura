package com.cultura.model;

public class AuthVO {
    private String userId;
    private String auth;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAuth() {
        return auth;
    }
    public void setAuth(String auth) {
        this.auth = auth;
    }
    @Override
    public String toString() {
        return "AuthVO [userId=" + userId + ", auth=" + auth + "]";
    }   
    
}
