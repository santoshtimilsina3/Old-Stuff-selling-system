/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import com.syntech.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sagar
 */
public class UserRepository implements IDatabase<Customer> {
    
    private Map<String,User> users = new HashMap<String,User>();

    @Override
    public void saveToDB(Customer customerDetail) {
         users.put(customerDetail.getUserName(),customerDetail);
         System.out.println(users);
     }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
    
}
