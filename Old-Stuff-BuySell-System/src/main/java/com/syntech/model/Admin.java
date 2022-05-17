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
public class Admin extends User{
    private Long id;

    public Admin(Long id, String name, String password, Long phone, String email, String userName) {
        super(name, password, phone, email, userName);
        this.id =id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
