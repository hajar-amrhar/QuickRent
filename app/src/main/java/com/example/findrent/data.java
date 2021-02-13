package com.example.findrent;

public class data {
    String username,email,phone,password1;

    public data(String username,String phone,String password1,String email) {
        this.username = username;
        this.password1=password1;
        //this.password2=password2;
        this.phone=phone;
        this.email=email;
    }

    public data() {
        }



    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
