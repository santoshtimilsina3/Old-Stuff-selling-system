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
public class AllRecords implements ITableInfo {
    private Long Cid;
    private String name;
    private Long phone;
    private Long Iid;
    private String IName;
    private Float realPrice;
    private Float sellingPrice;
    private Float buyingPrice;
    private Boolean type;

    public AllRecords(Long Cid, String name, Long phone, Long Iid, String IName, Float realPrice, Float sellingPrice, Float buyingPrice, Boolean type) {
        this.Cid = Cid;
        this.name = name;
        this.phone = phone;
        this.Iid = Iid;
        this.IName = IName;
        this.realPrice = realPrice;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.type = type;
    }

    public Long getCid() {
        return Cid;
    }

    public void setCid(Long Cid) {
        this.Cid = Cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getIid() {
        return Iid;
    }

    public void setIid(Long Iid) {
        this.Iid = Iid;
    }

    public String getIName() {
        return IName;
    }

    public void setIName(String IName) {
        this.IName = IName;
    }

    public Float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Float realPrice) {
        this.realPrice = realPrice;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.Cid);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.Iid);
        hash = 37 * hash + Objects.hashCode(this.IName);
        hash = 37 * hash + Objects.hashCode(this.realPrice);
        hash = 37 * hash + Objects.hashCode(this.sellingPrice);
        hash = 37 * hash + Objects.hashCode(this.buyingPrice);
        hash = 37 * hash + Objects.hashCode(this.type);
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
        final AllRecords other = (AllRecords) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.IName, other.IName)) {
            return false;
        }
        if (!Objects.equals(this.Cid, other.Cid)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.Iid, other.Iid)) {
            return false;
        }
        if (!Objects.equals(this.realPrice, other.realPrice)) {
            return false;
        }
        if (!Objects.equals(this.sellingPrice, other.sellingPrice)) {
            return false;
        }
        if (!Objects.equals(this.buyingPrice, other.buyingPrice)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }


    @Override
    public Tables getTypeOfSheet(){
                   
        return Tables.ALLRECORDS;
  }

  
    
    
   
    
}
