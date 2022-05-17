/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

/**
 *
 * @author sagar
 */
public class User {

    private String name;
    private String password;
    private Long phone;
    private String email;
    private String userName;

    public User(String name, String password, Long phone, String email, String userName) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", password=" + password + ", phone=" + phone + ", email=" + email + ", userName=" + userName + '}';
    }

}
