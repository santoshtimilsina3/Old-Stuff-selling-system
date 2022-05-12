/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author sagar
 */
public class ExcelOperationRepository {
    public void appendExcelData(Object[] tableData,String sheetName){
          String excelFilePath="/home/sagar/NetBeansProjects/book/oldsell.xls";
        try {
            Workbook workbook;
              try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
                  workbook = WorkbookFactory.create(inputStream);
                  Sheet sheet = workbook.getSheet(sheetName);
                  int rowCount = sheet.getLastRowNum();
                  System.out.println(rowCount);
                  System.out.println(sheet.getPhysicalNumberOfRows());
                  Row row = sheet.createRow(++rowCount);
                  int columnCount = -1;
                  System.out.println("execel1");
                  
                  for (Object field : tableData) {
                      Cell cell = row.createCell(++columnCount);
                      if (field instanceof String) {
                          cell.setCellValue((String) field);
                      } else if (field instanceof Integer) {
                          cell.setCellValue((Integer) field);
                      }else if(field instanceof Long){
                          cell.setCellValue((Long)field);
                      }else if(field instanceof Boolean){
                          cell.setCellValue((Boolean) field);
                      }
                  }     
                  inputStream.close();
              }              
              try (
                   FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                  workbook.write(outputStream);
                  workbook.close();
                  outputStream.close();
              }
             
       
    }catch(IOException | EncryptedDocumentException | InvalidFormatException e){
        System.out.println(e);
    }        
    }
}

