/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.repository.JDBC;
import com.syntech.util.Bycrypt;
import com.syntech.util.OwnScanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sagar
 */
public class LoginController {

    JDBC jdbc = new JDBC();
    LoggedInMenuController log = new LoggedInMenuController();

    public void signIn() {
        System.out.println("enter your userName");
        String userName = OwnScanner.scan().next();
        System.out.println("enter your password");
        String password = OwnScanner.scan().next();

        Customer userInfo = jdbc.checkCustomerCredential(userName, password);
        if (userInfo == null) {
            System.out.println("Wrong user credential !!!");
            return;
        }
        System.out.println(" login sucess !!!");
        log.LoggedInMenu(userInfo);

    }

    public void registerNewUser() {
        System.out.println("press 1.  temporary user");
        System.out.println("press 2.   permanent User");
        System.out.println("press 3.   Main Menu");
        byte choice = OwnScanner.scan().nextByte();

        switch (choice) {
            case 1:
                temporaryUser();

                break;
            case 2:
                savePermanentUser();
                break;
            case 3:
                return;

            default:
                System.out.println("Enter valid input !!! LoginController");

        }
    }

    public void temporaryUser() {
        try {
            Customer tempCustomer = takeInput();
        } catch (Exception e) {
            System.out.println("enter valid input ~~~ temporary user");

        }

    }

    public void savePermanentUser() {
        try {
            Customer userInfo = takeInput();
            List<ITableInfo> customerList = new ArrayList<>();
            customerList.add(userInfo);
            jdbc.writeToDatabase(customerList);
            signIn();

        } catch (Exception e) {
            System.out.println("Enter valid input !!! permanent user Logincontroller" + e);

        }

    }

    public Customer takeInput() throws Exception {

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
        String unHashedpassword = OwnScanner.scan().next();
        String hashedPassword=Bycrypt.hashString(unHashedpassword);

        Customer newCustomer = new Customer(null, address, name, hashedPassword, phone, email, userName);
        return newCustomer;

    }
}
