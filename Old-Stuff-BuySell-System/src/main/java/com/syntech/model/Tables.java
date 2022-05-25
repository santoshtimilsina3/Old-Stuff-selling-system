/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.syntech.model;

/**
 *
 * @author sagar
 */
public enum Tables {
    ITEMS("Items", "available_item"),
    CUSTOMER("Customer", "customer"),
    ALLRECORDS("allRecords", "bought_item"),
    BoughtItems("boughtItems","bought_item");

    public final String sheetName;
    public final String tableName;

    private Tables(String sheetName, String tableName) {
        this.sheetName = sheetName;
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSheetName() {
        return sheetName;
    }

}
