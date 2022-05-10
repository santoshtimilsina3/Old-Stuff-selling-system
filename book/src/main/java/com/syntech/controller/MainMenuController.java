/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;
import com.syntech.model.Items;
import com.syntech.util.OwnScanner;

/**
 *
 * @author sagar
 */
public class MainMenuController {
    

/*view available items and buy the item that user want to buy and remove the bought item from available database */
    
    public void buyItems() {
        LoggedInMenuController.view.viewAvailableItems();
          String result =  LoggedInMenuController.view.buyItemMenu();
          if(result!=null){
              LoggedInMenuController.itemInsert.getItems().get(result).setType(false);             
            
        }

                
              
    }
    /* Take the input from the user about the description of items they want to sale and save them in database */

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

        Items item = new Items(id, name, realPrice, sellingPrice,true);
        
        LoggedInMenuController.itemInsert.saveToDB(item);
        
        }catch(Exception e){
            System.out.println("enter the valid input "+e);
        }
        }
    
    
        
    }
class ViewOperation{
    
    /* check the available items that are for sale and print them in the console*/
    
public void viewAvailableItems(){
                        for (Items val : LoggedInMenuController.itemInsert.getItems().values()){
                            if(val.getType()){
                                System.out.println(val);
                            }
    }
    
                
               }
/* Take the input from the user the item they want to buy and check if the input is correct and return name of user typed input if 
the item is available for sale
*/
public String buyItemMenu(){
                    System.out.println("Enter the Name of the item you want to buy");
                    String choice=OwnScanner.scan().next();
                    
                    if(LoggedInMenuController.itemInsert.getItems().containsKey(choice)){
                    return choice;
                    }                  
               return null;
}
}

