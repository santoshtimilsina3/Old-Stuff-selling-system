/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.*;

/**
 *
 * @author sagar
 */
public class VerifyDetailsController extends UserRepository {

    public boolean verifyUserCredential(String userName, String pass) {

        return MainController.ur.getUsers() != null && MainController.ur.getUsers().containsKey(userName) && MainController.ur.getUsers().get(userName).getPassword().equals(pass);
    }
}
