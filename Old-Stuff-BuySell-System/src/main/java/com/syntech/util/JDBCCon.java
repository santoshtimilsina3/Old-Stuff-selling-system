/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sagar
 */
public class JDBCCon {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/old_item_sell";
    private  static String USER = "root";
    private  static String PASSWORD = "toor";
    private static Connection connection = null;
    private JDBCCon(){
        }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);            
        }
        return connection;
    }
          

    public static void closeConnection(){
         if (connection != null) try { connection.close(); 
            connection=null;
         } catch (SQLException ignore) {
         System.out.println("ERROR ON CLOSE CONNECTION");}
  }

}
