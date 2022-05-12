/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;
/**
 *
 * @author sagar
 */
public class VerifyDetailsController  {

    public boolean verifyUserCredential(String userName, String pass) {
             boolean nullAndKeyCheck,passwordMatch;
             nullAndKeyCheck = MainController.userRepo.getUsers()!=null && MainController.userRepo.getUsers().containsKey(userName) ;
             passwordMatch= MainController.userRepo.getUsers().get(userName).getPassword().equals(pass);

        return nullAndKeyCheck && passwordMatch;
    }
}
