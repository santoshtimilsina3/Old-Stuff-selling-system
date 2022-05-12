/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.repository.ExcelOperationRepository ;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class LoginController {
    ExcelOperationRepository  excel=new ExcelOperationRepository ();
    LoggedInMenuController log=new LoggedInMenuController();

    public void signIn() {
        System.out.println("enter your userName");
       String userName = OwnScanner.scan().next();
        System.out.println("enter your password");
        String password = OwnScanner.scan().next();
         VerifyDetailsController vd = new VerifyDetailsController();
        
        if(!vd.verifyUserCredential(userName, password)){
           return;            
        }
        
         System.out.println("login sucesss !!! \n");
         log.LoggedInMenu(userName);
       
    }

    /* Entering the new user credential and storing them in the database */
    
    public void registerNewUser() {
         System.out.println("press 1.  temporary user");
        System.out.println("press 2.   permanent User");
        System.out.println("press 3.   Main Menu");
       byte choice =OwnScanner.scan().nextByte();
        
        switch(choice){
            case 1: temporaryUser();
            
                    break;
            case 2: permanentUser();
                    break;
            case 3: return;
                     
            default: System.out.println("Enter valid input !!! ");

        
     }
    }
    
    public void temporaryUser(){
        try {
        Customer tempCustomer =takeMenuInput();
        MainController.userRepo.saveToDB(tempCustomer);
    
    

    
            }catch(Exception e){
    
}     
       
    }

    public void permanentUser(){ 
        try{
          Customer permanent= takeMenuInput();
            
      Object[] userData={permanent.getId(),permanent.getAddress(),permanent.getName(),permanent.getPassword(),permanent.getPhone(),permanent.getEmail(),permanent.getUserName()};
        excel.appendExcelData(userData,"Customer");
            
        }catch(Exception e ){
            System.out.println("Enter valid input !!!");          
        
        }


            }
    public Customer takeMenuInput() throws Exception {
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


