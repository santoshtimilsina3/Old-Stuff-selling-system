/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.BoughtItems;
import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.model.Items;
import com.syntech.repository.JDBC;
import com.syntech.util.OwnScanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santosh
 */
public class MainMenuController {

    JDBC jdbc = new JDBC();
    // public ExcelOperationRepository excel = new ExcelOperationRepository();

    public void boughtItems(Customer userData) {
        jdbc.boughtItemListPrint(userData.getId());
    }
    

    /*view available items and buy the item that user want to buy and remove the bought item from available database */
    public void buyItems(Customer userData) {
        jdbc.getAvailableItem();
        System.out.println("enter the ID of item you want to buy");
        Long id = OwnScanner.scan().nextLong();
        Long availableId = jdbc.checkId(id);
        if (availableId == 0l || availableId == null) {
            System.out.println("ENTER VALID ID");
            return;
        }
        BoughtItems item = new BoughtItems(null, id, userData.getId());
        jdbc.updateItemAndBoughtTable(item);

        //        String name=OwnScanner.scan().next();
//       excel.excelBuyIteration(name,userData);
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
//            System.out.println("Enter your Item id");
//            Long id = OwnScanner.scan().nextLong();

            System.out.println("Enter your Item Name");
            String name = OwnScanner.scan().next();

            System.out.println("Enter Real price of your item");
            Float realPrice = OwnScanner.scan().nextFloat();

            System.out.println("Enter your selling price ");
            Float sellingPrice = OwnScanner.scan().nextFloat();

            //(Long id, String name, Float realPrice, Float sellingPrice,boolean type,Long customerId
            System.out.println(userData.getId());
             System.out.println(userData.getId());
            
            Items item = new Items(null,name,realPrice,sellingPrice,true,userData.getId());
            System.out.println(item);
            List<ITableInfo> list = new ArrayList();
            list.add(item);
            jdbc.writeToDatabase(list);

//           // Items item = new Items(id, name, realPrice, sellingPrice, true);
//          //  LoggedInMenuController.itemInsert.saveToDB(item);
//
//            List<ITableInfo> list = new ArrayList<>();
//            list.add(item);
//            excel.writeFile(list);
//
//            AllRecords records = new AllRecords(userData.getId(), userData.getName(), userData.getPhone(), id, name, realPrice, sellingPrice, 0F, true);
//            List<ITableInfo> record = new ArrayList<>();
//            record.add(records);
//            excel.writeFile(record);
        } catch (Exception e) {
            System.out.println("enter the valid input " + e);
        }
    }

}


