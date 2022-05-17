/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Santosh
 */
public class ExcelOperationRepository {

    public void appendExcelData(Object[] tableData, String sheetName) {
        String excelFilePath = "/home/sagar/NetBeansProjects/book/oldsell.xls";
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
                System.out.println("check if execution is coming in excel method");

                for (Object field : tableData) {
                    Cell cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    } else if (field instanceof Long) {
                        cell.setCellValue((Long) field);
                    } else if (field instanceof Boolean) {
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

        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            System.out.println(e);
        }
    }

    public Customer loginExcelIteration(String userName, String password, String sheetName) {
        String excelFilePath = "/home/sagar/NetBeansProjects/book/oldsell.xls";

        Workbook workbook;
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            workbook = WorkbookFactory.create(inputStream);
            HSSFWorkbook wb = new HSSFWorkbook();
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println("inside loginexceliteration");

            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    //  cell = (HSSFCell)cell;

                    switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
                        case NUMERIC:
                            //System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            if (cell.getStringCellValue().equalsIgnoreCase(password)) {
                                if (cell.getStringCellValue().equalsIgnoreCase(userName)) {
                                    System.out.println("suceessss");
                                    getRowData(row, formulaEvaluator);
                                }
                            }
                            break;
                        case _NONE:
                            break;
                        case FORMULA:
                            break;
                        case BLANK:
                            break;
                        case BOOLEAN:

                            break;
                        case ERROR:
                            break;
                        default:
                            break;

                    }

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer getRowData(Row row, FormulaEvaluator formulaEvaluator) {
        Object[] rowData = new Object[row.getRowNum()];

        Integer size = 0;
        for (Cell cell : row) {
            Object data = null;
            switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
                case NUMERIC:
                    data = cell.getNumericCellValue();
                    break;
                case STRING:
                    data = cell.getStringCellValue();
                    break;
                case _NONE:
                    break;
                case FORMULA:
                    break;
                case BLANK:
                    break;
                case BOOLEAN:
                    data = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }
            rowData[++size] = data;
        }
        return null;
    }
}
