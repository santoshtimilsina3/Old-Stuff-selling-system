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
public class Customer extends User implements ITableInfo{

    private Long id;
    private String address;

    public Customer(Long id, String address, String name, String password, Long phone, String email, String userName) {
        super(name, password, phone, email, userName);
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", address=" + address + '}' + super.toString();
    }

    @Override
    public Tables getTypeOfSheet() {
        return Tables.CUSTOMER;
    }

  
}
