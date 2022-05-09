/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Items;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sagar
 */
public class ItemRepository implements IDatabase<Items> {

    public static List<Items> items = new ArrayList<Items>();
    @Override
    public void saveToDB(Items item) {
        items.add(item);
        System.out.println("listed items" + items);
    } 
}
