/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.ItemRepository;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class LoggedInMenuController {
   public static  MainMenuController menu= new MainMenuController();
   public static  ViewOperation view=new ViewOperation();
   public static ItemRepository itemInsert=new ItemRepository();
   public static LoggedInMenuController recordDatabase=new LoggedInMenuController();
    
     public void LoggedInMenu(String name){
     System.out.println("---------------------------------------------\n");
     System.out.println("Welcome Mr/mrs :- "+ MainController.userRepo.getUsers().get(name).getName()+" \n");
     System.out.println("--------------------------------------------- \n");
    while(true){
        try{   
    System.out.println("Press 1 . BuyItems               ");
    System.out.println("Press 2 . Sell Items               ");
    System.out.println("Press 3 . For view Available Items");
    System.out.println("Press 0 . LogOut                    ");
    byte choice =OwnScanner.scan().nextByte();
    switch(choice){
        case 1: menu.buyItems();
                 break;
        case 2: menu.sellItems();
                 break;
        case 3: view.viewAvailableItems();
                break;
        case 0: return;
        default:System.out.println("please enter the valid input");
        
    }
    }
    catch(Exception e){
        System.out.println("please enter the valid input");
            
            }
}
     }
}