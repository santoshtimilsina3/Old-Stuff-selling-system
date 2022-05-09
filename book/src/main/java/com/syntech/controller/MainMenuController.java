/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;
import com.syntech.repository.*;
import com.syntech.model.Items;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class MainMenuController {

    public void buyItems() {
        

    }

    public void sellItems() {
        try{
        System.out.println("Enter your Item id");
        Long id = OwnScanner.scan().nextLong();

        System.out.println("Enter your Item Name");
        String name = OwnScanner.scan().next();

        System.out.println("Enter Real price of your item");
        float realPrice = OwnScanner.scan().nextFloat();

        System.out.println("Enter your selling price ");
        float sellingPrice = OwnScanner.scan().nextFloat();
        
            

        Items item = new Items(id, name, realPrice, sellingPrice);
        ItemRepository db=new ItemRepository();
        db.saveToDB(item);
        }catch(Exception e){
            System.out.println("enter the valid input "+e);
        }
        }
    }

