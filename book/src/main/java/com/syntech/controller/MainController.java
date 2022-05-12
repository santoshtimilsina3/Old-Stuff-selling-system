package com.syntech.controller;

import com.syntech.repository.ItemRepository;
import com.syntech.repository.UserRepository;
import com.syntech.util.OwnScanner;

/**
 * @author sagar
 */
public class MainController {
    public static LoginController login=new LoginController();
    public static UserRepository userRepo=new UserRepository();
    public static ItemRepository item=new ItemRepository();
    public static void main(String... args) {
        
        while (true) {
            try{
            System.out.println("**********************************************");
            System.out.println("                                              ");
            System.out.println("    Welcome to Old Stuff Sale Portal          ");
            System.out.println("                                              ");
            System.out.println("**********************************************");

            System.out.println("Press 1  :-  Login ");
            System.out.println("Press 2  :-  Register New User");
            System.out.println("press 0  :-  Exit");
            byte choice = OwnScanner.scan().nextByte();
            switch (choice) {
                case 1:
                    login.signIn();
                    break;
                case 2:
                    login.registerNewUser();                   
                   break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Enter valid number !!!");
            }
        
        }catch(Exception e){
            System.out.println("Enter valid input !!!! Main "+e);
            System.exit(0);
        }
        }
    }
}
