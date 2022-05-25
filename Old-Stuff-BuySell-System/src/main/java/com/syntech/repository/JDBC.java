/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.BoughtItems;
import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.model.Items;
import com.syntech.util.JDBCCon;
import com.syntech.util.OwnScanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sagar
 */
public class JDBC {

    public void writeToDatabase(List<ITableInfo> list) {
        try {
            System.out.println(list);
            Connection connection = JDBCCon.getConnection();
            for (ITableInfo table : list) {
                 System.out.println(table);
                insertIntoTable(table, connection);
               
            }
        } catch (SQLException e) {
            System.out.println("Database ERROR !!!");
            System.out.println(e);
        } finally {
            JDBCCon.closeConnection();
        }
    }

    private void insertIntoTable(ITableInfo table, Connection connection) throws SQLException {
        System.out.println(table.getTypeOfSheet().getTableName());
        switch (table.getTypeOfSheet().getTableName()) {
            case "customer":
                Customer customer = (Customer) table;
                String Customerquery = "insert into customer(address,name,password,phone,email,user_name) values(?,?,?,?,?,?)";
                PreparedStatement CStatement = connection.prepareStatement(Customerquery);
                CStatement.setString(1, customer.getAddress());
                CStatement.setString(2, customer.getName());
                CStatement.setString(3, customer.getPassword());
                CStatement.setLong(4, customer.getPhone());
                CStatement.setString(5, customer.getEmail());
                CStatement.setString(6, customer.getUserName());
                CStatement.executeUpdate();
                break;

            case "available_item":
                Items item = (Items) table;
                String itemQuery = "insert into available_item(item_name,real_price,selling_price,is_available,customer_id) values(?,?,?,?,?)";
                PreparedStatement IStatement = connection.prepareStatement(itemQuery);
                IStatement.setString(1, item.getName());
                IStatement.setFloat(2, item.getRealPrice());
                IStatement.setFloat(3, item.getSellingPrice());
                IStatement.setBoolean(4, item.getType());
                IStatement.setLong(5, item.getCustomerId());
                IStatement.executeUpdate();
                
                break;

            case "bought_item":
                BoughtItems boughtItem = (BoughtItems) table;
                String boughtQuery = "insert into bought_item(available_id,customer_id) values(?,?)";
                PreparedStatement boughtTableStatement = connection.prepareStatement(boughtQuery);
                boughtTableStatement.setLong(1, boughtItem.getItemsId());
                boughtTableStatement.setLong(2, boughtItem.getCustomerId());
                break;

        }

    }

    public void boughtItemListPrint(Long id) {
        try {
            Connection connection = JDBCCon.getConnection();
            String query = "select available_item.id ,available_item.item_name ,available_item.selling_price from available_item join bought_item on available_item.id=bought_item.available_id where bought_item.customer_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            System.out.println("ITEM ID " + "ITEM NAME " + "BOUGHT PRICE");
            printResultSet(result);
        } catch (SQLException e) {

        } finally {
            JDBCCon.closeConnection();
        }

    }

    private void printResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData myRs = result.getMetaData();
        int columnCount = myRs.getColumnCount();
        while (result.next()) {
            for (int startIndex = 1; startIndex <= columnCount; startIndex++) {
                System.out.print(result.getObject(startIndex));
            }
            System.out.println();

        }
    }

    public void seesoldItems(Long id) {
        try {
            Connection connection = JDBCCon.getConnection();
            String query = "select id ,item_name,selling_price from available_item where customer_id=? and is_available=false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            printResultSet(result);

        } catch (SQLException e) {

        } finally {
            JDBCCon.closeConnection();
        }
    }

    public void seeUnsoldItems(Long id) {
        try {
            Connection connection = JDBCCon.getConnection();
            String query = "select id ,item_name,real_price,selling_price from available_item where customer_id=? and is_available=true";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            printResultSet(result);

        } catch (SQLException e) {

        } finally {
            JDBCCon.closeConnection();
        }

    }
    public void deleteUnsoldItem(Long itemId,Long customerId){
        try{
        Connection connection = JDBCCon.getConnection();
            String query = "delete from available_item where id=? and is_available=true and customer_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, itemId);
            statement.setLong(2, customerId);
            int rowEffected=statement.executeUpdate();
            if(rowEffected<=0){
                System.out.println("Enter valid item ");
            }
        }catch(SQLException e){
            
        }finally{
            JDBCCon.closeConnection();
        }
        
    }
//    public void insertData(Customer customer) {
//        try {
//            Connection con = JDBCCon.getConnection();
//            //String address, String name, String password, Long phone, String email, String userName
//            String query = "insert into customer(address,name,password,phone,email,user_name) values(?,?,?,?,?,?)";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, customer.getAddress());
//            statement.setString(2, customer.getName());
//            statement.setString(3, customer.getPassword());
//            statement.setLong(4, customer.getPhone());
//            statement.setString(5, customer.getEmail());
//            statement.setString(6, customer.getUserName());
//            statement.execute();
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            JDBCCon.closeConnection();
//        }
//    }

    public Customer checkCustomerCredential(String inUserName, String inPassword) {
        try {
            Connection con = JDBCCon.getConnection();
            String query = "select * from customer where password=? and user_name=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, inPassword);
            statement.setString(2, inUserName);
            ResultSet result = statement.executeQuery();
            System.out.println("after result set");
            while (result.next()) {
                String pass = result.getString("password");
                String name = result.getString("user_name");
                if (pass.equals(inPassword) && name.equals(inUserName)) {
                    Long id = result.getLong(1);
                    String address = result.getString(2);
                    String CName = result.getString(3);
                    String CPass = result.getString(4);
                    Long phone = result.getLong(5);
                    String email = result.getString(6);
                    String user_name = result.getString(7);
                    System.out.println(id + name + CName + CPass + phone + email + user_name);
                    Customer customer = new Customer(id, address, CName, CPass, phone, email, user_name);
                    return customer;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            JDBCCon.closeConnection();
        }
        return null;
    }

    public void getAvailableItem() {
        try {
            Connection connection = JDBCCon.getConnection();
            String sql = "select id,item_name,selling_price from available_item where is_available=true";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("item Id  " + " " + "item Name " + " " + "buying Price");
            printResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            JDBCCon.closeConnection();
        }
    }

    public Long checkId(Long id) {
        try {
            Connection connection = JDBCCon.getConnection();
            String query = "select id from available_item where is_available=true and id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return id;
            }

        } catch (SQLException e) {

        } finally {
            JDBCCon.closeConnection();
        }
        return 0l;

    }

    public void updateItemAndBoughtTable(BoughtItems item) {
        try {
            Connection connection = JDBCCon.getConnection();
            connection.setAutoCommit(false);
            String itemTableUpdate = "update available_item set is_available=false where id=?";
            String boughtTableInsert = "insert into bought_item(available_id,customer_id) values(?,?)";
            PreparedStatement itemTableStatement = connection.prepareStatement(itemTableUpdate);
            itemTableStatement.setLong(1, item.getItemsId());
            PreparedStatement boughtTableStatement = connection.prepareStatement(boughtTableInsert);
            boughtTableStatement.setLong(1, item.getItemsId());
            boughtTableStatement.setLong(2, item.getCustomerId());
            itemTableStatement.executeUpdate();
            boughtTableStatement.executeUpdate();
            if (isUserOk()) {
                connection.commit();
                return;
            }
            connection.rollback();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            JDBCCon.closeConnection();
        }

    }
      private boolean isUserOk() {
        System.out.println("Are you sure you want to buy? yes/No");
        String choice = OwnScanner.scan().next();
        System.out.println(choice);
        return (choice.equals("yes") || choice.equals("y"));
    }

  

}
