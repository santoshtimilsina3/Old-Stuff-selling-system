/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.AllRecords;
import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.model.Items;
import com.syntech.repository.ExcelOperationRepository;
import com.syntech.util.OwnScanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santosh
 */
public class MainMenuController {

    ExcelOperationRepository excel = new ExcelOperationRepository();

    /*view available items and buy the item that user want to buy and remove the bought item from available database */
    public void buyItems(Customer userData) {
        excel.viewAvailableItem();
        System.out.println("enter the name of item you want to buy");
        String name=OwnScanner.scan().next();
       excel.excelBuyIteration(name,userData);
      //  LoggedInMenuController.view.viewAvailableItems();
       // String result = LoggedInMenuController.view.buyItemMenu();
//        if (result == null){
//            return;
//        }
     //   LoggedInMenuController.itemInsert.getItems().get(result).setType(false);

    }

    /* Take the input from the user about the description of items they want to sale and save them in database */
    public void sellItems(Customer userData) {
        try {
            System.out.println("Enter your Item id");
            Long id = OwnScanner.scan().nextLong();

            System.out.println("Enter your Item Name");
            String name = OwnScanner.scan().next();

            System.out.println("Enter Real price of your item");
            Float realPrice = OwnScanner.scan().nextFloat();

            System.out.println("Enter your selling price ");
            Float sellingPrice = OwnScanner.scan().nextFloat();

            Items item = new Items(id, name, realPrice, sellingPrice, true);
            LoggedInMenuController.itemInsert.saveToDB(item);

            List<ITableInfo> list = new ArrayList<>();
            list.add(item);
            excel.writeFile(list);

            AllRecords records = new AllRecords(userData.getId(), userData.getName(), userData.getPhone(), id, name, realPrice, sellingPrice, 0F, true);
            List<ITableInfo> record = new ArrayList<>();
            record.add(records);
            excel.writeFile(record);

        } catch (Exception e) {
            System.out.println("enter the valid input " + e);
        }
    }

}

class ViewOperation {

    ExcelOperationRepository excel = new ExcelOperationRepository();

    /* check the available items that are for sale and print them in the console*/
    public void viewAvailableItmes() {
        excel.viewAvailableItem();
    }

    public void TempviewAvailableItems() {
        for (Items val : LoggedInMenuController.itemInsert.getItems().values()) {
            if (val.getType()) {
                System.out.println(val);
            }
        }

    }

    /* Take the input from the user the item they want to buy and check if the input is correct and return name of user typed input if
    the item is available for sale
     */
    public String buyItemMenu() {
        System.out.println("Enter the Name of the item you want to buy");
        String choice = OwnScanner.scan().next();

        if (LoggedInMenuController.itemInsert.getItems().containsKey(choice)) {
            return choice;
        }
        return null;
    }
}
