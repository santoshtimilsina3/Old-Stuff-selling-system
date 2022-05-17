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
public class Items {
    private Long id;
    private String name;
    private float realPrice;
    private float sellingPrice;
    private boolean type  ;
    

    public Items(Long id, String name, float realPrice, float sellingPrice,boolean type) {
        this.id = id;
        this.name = name;
        this.realPrice = realPrice;
        this.sellingPrice = sellingPrice;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Items{" + "id=" + id + ", name=" + name + ", realPrice=" + realPrice + ", sellingPrice=" + sellingPrice + ", type=" + type + '}';
    }

    
   

    
    
    
    
}
