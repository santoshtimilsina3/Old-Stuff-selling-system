/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author sagar
 */
public class ExcelDatabase implements IDatabase<Customer> {

    @Override
    public void saveToDB(Customer details) {
        try {
         Workbook wb = new HSSFWorkbook();//creating a instance of workbook class
		//create and excel file in the specified location
		OutputStream fileOut =new FileOutputStream("/home/sagar/NetBeansProjects/book/excel.xlsx");
		
        } catch (FileNotFoundException ex) {
            System.out.println("File Error !! " + ex);
        }
    }

}
