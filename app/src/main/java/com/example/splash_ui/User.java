package com.example.splash_ui;

public class User {
    private String full_name,username,email,phone,password;
    User()
    {

    }

    User(String full_name,String username,String email,String phone,String password){
        this.full_name=full_name;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.password=password;


    }
    public String getName() {
        return full_name;
    }

    public void setName(String full_name) {
        this.full_name = full_name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

