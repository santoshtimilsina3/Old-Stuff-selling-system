/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;


/**
 *
 * @author sagar
 */
public interface IDatabase<T> {

    void saveToDB(T t);
}

