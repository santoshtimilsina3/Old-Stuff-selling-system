/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.repository.UserRepository;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class LoginController {

    public void signIn() {
        System.out.println("enter your userName");
       String userName = OwnScanner.scan().next();
        System.out.println("enter your password");
        String password = OwnScanner.scan().next();
        VerifyDetailsController vd = new VerifyDetailsController();
        
        if(vd.verifyUserCredential(userName, password)){
            System.out.println("login sucesss !!!");
            System.out.println(MainController.ur.getUsers());
        }
        else{
            System.out.println("Incorrect user");
        };

    }

    /* Entering the new user credential and storing them*/
    public void registerNewUser() {
       try{      
        System.out.println("Enter your id");
        Long id = OwnScanner.scan().nextLong();

        System.out.println("Enter your fullName");
        String name = OwnScanner.scan().next();

        System.out.println("enter your Email");
        String email = OwnScanner.scan().next();

        System.out.println("enter your phone number");
        Long phone = OwnScanner.scan().nextLong();

        System.out.println("Enter your address");
        String address = OwnScanner.scan().next();

        System.out.println("enter your userName");
        String userName = OwnScanner.scan().next();

        System.out.println("enter your password");
        String password = OwnScanner.scan().next();

        Customer newCustomer = new Customer(id, address, name, password, phone, email, userName);
       
        MainController.ur.saveToDB(newCustomer);
        System.out.println(MainController.ur.getUsers());

       }catch(Exception e){
           System.out.println("Enter the valid input ");
    }


    }
}