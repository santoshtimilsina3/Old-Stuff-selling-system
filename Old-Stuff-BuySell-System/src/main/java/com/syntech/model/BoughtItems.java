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
public class BoughtItems implements ITableInfo{
    private Long id;
    private Long ItemsId;
    private Long CustomerId;

    public BoughtItems(Long id, Long ItemsId, Long CustomerId) {
        this.id = id;
        this.ItemsId = ItemsId;
        this.CustomerId = CustomerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemsId() {
        return ItemsId;
    }

    public void setItemsId(Long ItemsId) {
        this.ItemsId = ItemsId;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long CustomerId) {
        this.CustomerId = CustomerId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.ItemsId);
        hash = 43 * hash + Objects.hashCode(this.CustomerId);
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
        final BoughtItems other = (BoughtItems) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ItemsId, other.ItemsId)) {
            return false;
        }
        if (!Objects.equals(this.CustomerId, other.CustomerId)) {
            return false;
        }
        return true;
    }

    @Override
    public Tables getTypeOfSheet() {
        return Tables.BoughtItems;
    }


    
    
}
