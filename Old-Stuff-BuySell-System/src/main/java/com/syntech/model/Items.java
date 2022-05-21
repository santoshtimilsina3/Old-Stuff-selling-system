/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author sagar
 */
public class Items implements ITableInfo {
    private Long id;
    private String name;
    private Float realPrice;
    private Float sellingPrice;
    private boolean type;
       
    public Items(Long id, String name, Float realPrice, Float sellingPrice,boolean type) {
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

    public void setRealPrice(Float realPrice) {
        this.realPrice = realPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Items{" + "id=" + id + ", name=" + name + ", realPrice=" + realPrice + ", sellingPrice=" + sellingPrice + ", type=" + type + '}';
    }

    

    @Override
    public Tables getTypeOfSheet() {
        return Tables.ITEMS;
                        
   }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.realPrice);
        hash = 47 * hash + Objects.hashCode(this.sellingPrice);
        hash = 47 * hash + (this.type ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Items other = (Items) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.realPrice, other.realPrice)) {
            return false;
        }
        if (!Objects.equals(this.sellingPrice, other.sellingPrice)) {
            return false;
        }
        return true;
    }

  
}
