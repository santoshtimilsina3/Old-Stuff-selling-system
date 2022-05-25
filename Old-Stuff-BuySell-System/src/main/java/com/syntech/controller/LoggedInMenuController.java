/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Customer;
import com.syntech.repository.ItemRepository;
import com.syntech.repository.JDBC;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class LoggedInMenuController {

    public MainMenuController menu = new MainMenuController();
    public ItemRepository itemInsert = new ItemRepository();
    public JDBC jdbc = new JDBC();
    //  public LoggedInMenuController recordDatabase = new LoggedInMenuController();

    public void LoggedInMenu(Customer userData) {
        System.out.println("---------------------------------------------\n");
        System.out.println("Welcome Mr/mrs :- " + userData.getName() + " \n");
        System.out.println("--------------------------------------------- \n");
        while (true) {
            try {
                System.out.println("Press 1 . Item you have Bought       ");
                System.out.println("Press 2 . Items you have sold / placed for sell/ sell items");
                System.out.println("Press 3 . List of available items / Buy items");
                System.out.println("Press 0 . LogOut                     ");
                byte choice = OwnScanner.scan().nextByte();
                switch (choice) {
                    case 1:

                        menu.boughtItems(userData);
                        break;
                    case 2:
                        sellOperationMenu(userData);
                        break;
                    case 3:
                        menu.buyItems(userData);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("please enter the valid input");
                    //1. bought items 
                    //2. sell item list (sold or unsold)
                    //2.1 can delete unsold item
                    //3. list of available items
                    //3.1 can buy items

                }
            } catch (Exception e) {
                System.out.println("please enter the valid input");
                System.out.println("loginmenucontroller");
            }
        }
    }

    private void sellOperationMenu(Customer userData) {
        while (true) {
            System.out.println("press 1.  List of sold Items ");
            System.out.println("press 2.  List of  unsold items");
            System.out.println("press 3.  Sell  new item");
            System.out.println("press 0.  Return to main menu");
            byte choice = OwnScanner.scan().nextByte();
            switch (choice) {
                case 1:
                    jdbc.seesoldItems(userData.getId());
                    break;
                case 2:
                    jdbc.seeUnsoldItems(userData.getId());
                    System.out.println("Do you want to unsell the selling item");
                    String deleteChoice = OwnScanner.scan().next();
                    if (deleteChoice.equalsIgnoreCase("yes")) {
                        deleteUnsoldItems(userData.getId());
                    }
                    break;
                case 3:
                    System.out.println(userData+"selloperation menu ");
                        menu.sellItems(userData);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Enter valid input");
            }
        }

    }

    private void deleteUnsoldItems(Long customerId) {
        System.out.println("Enter the id of the item you want to delete ");
        Long itemId = OwnScanner.scan().nextLong();
        jdbc.deleteUnsoldItem(itemId, customerId);

    }
}
