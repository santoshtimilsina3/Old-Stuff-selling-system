package com.syntech.controller;

import com.syntech.repository.UserRepository;
import com.syntech.util.OwnScanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sagar
 */
public class MainController {
    public static LoginController lc=new LoginController();
    public static UserRepository ur=new UserRepository();
    public static void main(String... args) {
        try{
        while (true) {
            System.out.println("---------------------------------------------");
            System.out.println("                                             ");
            System.out.println("   Welcome to Old Stuff Sale Portal          ");
            System.out.println("                                             ");
            System.out.println("---------------------------------------------");

            System.out.println("Press 1  :-  Login ");
            System.out.println("Press 2  :-  Register New User");
            System.out.println("press 0  :-  Exit");
            byte choice = OwnScanner.scan().nextByte();
            switch (choice) {
                case 1:
                    lc.signIn();
                    break;
                case 2:
                    lc.registerNewUser();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Enter valid number !!!");
            }
        }
        }catch(Exception e){
            System.out.println("Enter valid input !!!! ");
        }
    }
}
