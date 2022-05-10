/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Items;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sagar
 */
public class ItemRepository implements IDatabase<Items> {

   private Map<String,Items> items = new HashMap<String,Items>();
    @Override
    public void saveToDB(Items item) {
        items.put(item.getName(),item);
        System.out.println("listed items" + items);
    }

    public Map<String, Items> getItems() {
        return items;
    }

    public void setItems(Map<String, Items> items) {
        this.items = items;
    }

  
    
    }

