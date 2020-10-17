package com.example.project_retail.requests;

public class SignupLogin_Requests {
    String name;
    String email;
    String password;

    public SignupLogin_Requests(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public SignupLogin_Requests(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignupLogin_Requests(SignupLogin_Requests signupLogin_requests) {
    }

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
}
