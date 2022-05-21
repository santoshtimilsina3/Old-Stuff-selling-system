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
public enum Tables{
     ITEMS("Items"),
    CUSTOMER("Customer"),
    ALLRECORDS("allRecords");
    
    public final String sheetName;
    
    private Tables(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }
    
    
}

