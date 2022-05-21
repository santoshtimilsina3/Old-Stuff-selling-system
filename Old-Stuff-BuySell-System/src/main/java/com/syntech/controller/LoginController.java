/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.repository.ExcelOperationRepository;
import com.syntech.util.OwnScanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author sagar
 */
public class LoginController {

    ExcelOperationRepository excel = new ExcelOperationRepository();
    LoggedInMenuController log = new LoggedInMenuController();

    public void signIn() throws IOException, InvalidFormatException {
        System.out.println("enter your userName");
        String userName = OwnScanner.scan().next();
        System.out.println("enter your password");
        String password = OwnScanner.scan().next();

         Customer userInfo= excel.loginExcelIteration(userName, password);
        if (userInfo != null) {
            System.out.println(" login sucess !!!");
            System.out.println(userInfo);
            log.LoggedInMenu(userInfo);
        }
        System.out.println("sign in method failed");

//         VerifyDetailsController vd = new VerifyDetailsController();
//        
//        if(!vd.verifyUserCredential(userName, password)){
//           return;            
//        }
//        
//         System.out.println("login sucesss !!! \n");
//      //   log.LoggedInMenu(userName);
    }

    /* Entering the new user credential and storing them in the database */
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
            MainController.userRepo.saveToDB(tempCustomer);
        } catch (Exception e) {
            System.out.println("enter valid input ~~~ temporary user");

        }

    }

    public void savePermanentUser() {
        try {
            Customer userInfo = takeInput();
            List<ITableInfo> userList = new ArrayList<>();
            userList.add(userInfo);
            System.out.println(userList + "permatuser");
            excel.writeFile(userList);
            signIn();

        } catch (Exception e) {
            System.out.println("Enter valid input !!! permanent user Logincontroller");

        }

    }

    public Customer takeInput() throws Exception {
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
        return newCustomer;

    }
}
