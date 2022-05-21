/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.AllRecords;
import com.syntech.model.Customer;
import com.syntech.model.ITableInfo;
import com.syntech.model.Items;
import com.syntech.model.Tables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Santosh
 */
public class ExcelOperationRepository {

    public void writeFile(List<ITableInfo> list) {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        try {
            File excelFile = new File(excelFilePath);
            if (!excelFile.exists()) {
                getWorkBook(excelFilePath);
            }
            FileInputStream file = new FileInputStream(excelFile);
            System.out.println("first");
            Workbook workbook = WorkbookFactory.create(file);
            writeToWorkBook(list, workbook);
            try (
                    FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
                file.close();
            }
        } catch (IOException | InvalidFormatException e) {
            System.out.println("Error inside ExcelRepo-writeFile");
        }
    }

    public Workbook getWorkBook(String excelFilePath) throws FileNotFoundException, IOException, InvalidFormatException {
        Workbook workbook;
        System.out.println("second");
        try (OutputStream fileOut = new FileOutputStream(excelFilePath)) {
            workbook = new HSSFWorkbook();
            workbook.createSheet(Tables.ALLRECORDS.getSheetName());
            workbook.createSheet(Tables.CUSTOMER.getSheetName());
            workbook.createSheet(Tables.ITEMS.getSheetName());
            fileOut.close();
        }
        return workbook;
    }

    public Sheet getSheet(ITableInfo table, Workbook workbook) {
        return workbook.getSheet(table.getTypeOfSheet().getSheetName());
    }

    public void writeToWorkBook(List<ITableInfo> list, Workbook workbook) {
        System.out.println("third");

        for (ITableInfo table : list) {
            Sheet sheet = getSheet(table, workbook);
            getrow(table, sheet);
        }
    }

    public Row getrow(ITableInfo table, Sheet sheet) {
        System.out.println("five");
        Row row = null;
        switch ((Tables) table.getTypeOfSheet()) {
            case ITEMS:
                Items item = (Items) table;
                System.out.println("getrow five");
                int itemEmptyRow = getEmptyRowIndex(sheet);
                row = sheet.createRow(itemEmptyRow);
                row.createCell(0).setCellValue(item.getId().toString());
                row.createCell(1).setCellValue(item.getName());
                row.createCell(2).setCellValue(item.getRealPrice());
                row.createCell(3).setCellValue(item.getSellingPrice());
                row.createCell(4).setCellValue(item.getType().toString());
                break;

            case CUSTOMER:
                Customer customer = (Customer) table;
                System.out.println("customer five");
                int customerEmptyRow = getEmptyRowIndex(sheet);
                row = sheet.createRow(customerEmptyRow);
                //Long id, String address, String name, String password, Long phone, String email, String userName

                row.createCell(0).setCellValue(customer.getId().toString());
                row.createCell(1).setCellValue(customer.getAddress());
                row.createCell(2).setCellValue(customer.getName());
                row.createCell(3).setCellValue(customer.getPassword());
                row.createCell(4).setCellValue(customer.getPhone().toString());
                row.createCell(5).setCellValue(customer.getEmail());
                row.createCell(6).setCellValue(customer.getUserName());

                break;

            case ALLRECORDS:
                AllRecords allRecords = (AllRecords) table;
                int recordsEmptyRow = getEmptyRowIndex(sheet);
                row = sheet.createRow(recordsEmptyRow);
                row.createCell(0).setCellValue(allRecords.getCid().toString());
                row.createCell(1).setCellValue(allRecords.getName());
                row.createCell(2).setCellValue(allRecords.getPhone().toString());
                row.createCell(3).setCellValue(allRecords.getIid().toString());
                row.createCell(4).setCellValue(allRecords.getIName());
                row.createCell(5).setCellValue(allRecords.getRealPrice().toString());
                row.createCell(6).setCellValue(allRecords.getSellingPrice().toString());
                row.createCell(7).setCellValue(allRecords.getBuyingPrice().toString());
                row.createCell(8).setCellValue(allRecords.getType().toString());
                break;
        }
        return row;
    }

    private int getEmptyRowIndex(Sheet sheet) {
        int index = 1;
        System.out.println("emptymethod");
        for (Row row : sheet) {
            row = sheet.getRow(index);
            if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().equals("")) {
                break;
            }
            index++;
        }
        System.out.println(index);
        return index;
    }

    public Customer loginExcelIteration(String userName, String password) throws IOException, InvalidFormatException {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        Workbook workbook;
        Customer userData = null;
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Customer");
            int index = 1;

            for (Row row : sheet) {
                row = sheet.getRow(index);
                if (row.getCell(3).getStringCellValue().equals(password) && row.getCell(6).getStringCellValue().equals(userName)) {
                    System.out.println("sucess!!!");
                    userData = getCustomerObject(index, sheet);
                    break;
                }
                index++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return userData;

    }

    private Customer getCustomerObject(int index, Sheet sheet) {
        //Long id, String address, String name, String password, Long phone, String email, String userName

        Long id = Long.parseLong(sheet.getRow(index).getCell(0).getStringCellValue());
        String address = sheet.getRow(index).getCell(1).getStringCellValue();
        String name = sheet.getRow(index).getCell(2).getStringCellValue();
        String password = sheet.getRow(index).getCell(3).getStringCellValue();
        Long phone = Long.parseLong(sheet.getRow(index).getCell(4).getStringCellValue());
        String email = sheet.getRow(index).getCell(5).getStringCellValue();
        String userName = sheet.getRow(index).getCell(6).getStringCellValue();
        return new Customer(id, address, name, password, phone, email, userName);
    }

    public void viewAvailableItem() {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        Workbook workbook;
        Customer userData = null;
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Items");
            for (Row row : sheet) {
                if (row.getCell(4).getStringCellValue().equals("true")) {

                    System.out.println(row.getCell(0).toString() + "|" + row.getCell(1).toString() + "|" + row.getCell(3).toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excelBuyIteration(String name, Customer userData) {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        Workbook workbook;
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Items");
            Long itemId = null;
            String itemName = null;
            Float realPrice = null;
            Float buyPrice = null;
            Boolean type = true;
            boolean status = false;
            int index = 1;
            System.out.println("hello i am in excel buy iteration");
            for (Row row : sheet) {
                row = sheet.getRow(index);
                if (row != null && row.getCell(1).getStringCellValue().equals(name) && row.getCell(4).getStringCellValue().equals("true")) {
                    System.out.println(row.getCell(4).getCellType());
                    System.out.println("sucess!!!");
                    itemId = Long.parseLong(row.getCell(0).getStringCellValue());
                    itemName = row.getCell(1).getStringCellValue();
                    realPrice = Float.parseFloat(formatter.formatCellValue(row.getCell(2)));
                    buyPrice = Float.parseFloat(formatter.formatCellValue(row.getCell(3)));
                    System.out.println(itemId + itemName + realPrice + buyPrice);
                    type = false;
                    status = true;
                    System.out.println(itemId + itemName + realPrice + buyPrice + sheet.getSheetName() + index);
                    System.out.println("lastof buy iteration!!!");
                    updateItem(index);
                    break;
                }
                index++;
            }
            if (status) {
                //Long id, String name, Float realPrice, Float sellingPrice,boolean type
                Items item = new Items(itemId, itemName, realPrice, 0f, type);
                List<ITableInfo> itemList = new ArrayList<>();
                itemList.add(item);
                ExcelOperationRepository excel = new ExcelOperationRepository();

                AllRecords record = new AllRecords(userData.getId(), userData.getName(), userData.getPhone(), itemId, itemName, realPrice, 0f, buyPrice, type);
                List<ITableInfo> list = new ArrayList<>();
                list.add(record);
                //  ExcelOperationRepository excel = new ExcelOperationRepository();
                excel.writeFile(list);
                return;
            }
            System.out.println("enter valid input item not found !!!");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void updateItem(int rowIndex) {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        try {
            File excelFile = new File(excelFilePath);
            FileInputStream file = new FileInputStream(excelFile);
            System.out.println("first");
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet=workbook.getSheet("Items");
            sheet.getRow(rowIndex).getCell(0).setCellValue("");
            sheet.getRow(rowIndex).getCell(1).setCellValue("");
            sheet.getRow(rowIndex).getCell(2).setCellValue("");
            sheet.getRow(rowIndex).getCell(3).setCellValue("");
            sheet.getRow(rowIndex).getCell(4).setCellValue("");

            try (
                    FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
                file.close();
            }
        } catch (IOException | InvalidFormatException e) {
            System.out.println("Error inside ExcelRepo-writeFile");
        }
    }

    private void updateItemTable(Sheet sheet, int rowIndex) throws InvalidFormatException {
        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
        Workbook workbook;
        try {
            FileInputStream file = new FileInputStream(new File(excelFilePath));
            workbook = WorkbookFactory.create(file);
            System.out.println("updateitemtable before loop");
            sheet.getRow(rowIndex).getCell(0).setCellValue("");
            sheet.getRow(rowIndex).getCell(1).setCellValue("");
            sheet.getRow(rowIndex).getCell(2).setCellValue("");
            sheet.getRow(rowIndex).getCell(3).setCellValue("");
            sheet.getRow(rowIndex).getCell(4).setCellValue("");

            try (
                    FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Error inside ExcelRepo-writeFile");
        }
    }
}

//    public Object[] getRowData(Row row, FormulaEvaluator formulaEvaluator) {
//        Object[] rowData = new Object[row.getRowNum()];
//
//        Integer size = 0;
//        for (Cell cell : row) {
//            Object data = null;
//            switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
//                case NUMERIC:
//                    data = cell.getNumericCellValue();
//                    break;
//                case STRING:
//                    data = cell.getStringCellValue();
//                    break;
//                case _NONE:
//                    break;
//                case FORMULA:
//                    break;
//                case BLANK:
//                    break;
//                case BOOLEAN:
//                    data = cell.getBooleanCellValue();
//                    break;
//                case ERROR:
//                    break;
//                default:
//                    break;
//            }
//            rowData[++size] = data;
//        }
//        return rowData;
//    }
//    public <ITableInfo>List loginExcelIteration(String userName, String password, String sheetName) {
//        String excelFilePath = "/home/sagar/NetBeansProjects/Old-Stuff-BuySell-System/oldsell.xls";
//        Workbook workbook;
//        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
//            workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheet(sheetName);
//            System.out.println("inside loginexceliteration");
//
//            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    //  cell = (HSSFCell)cell;
//
//                    switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
//                        case NUMERIC:
//                            //System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case STRING:
//                            if (cell.getStringCellValue().equalsIgnoreCase(password)) {
//                                if (cell.getStringCellValue().equalsIgnoreCase(userName)) {
//                                    System.out.println("suceessss");
//                                    getRowData(row, formulaEvaluator);
//                                }
//                            }
//                            break;
//                        case _NONE:
//                            break;
//                        case FORMULA:
//                            break;
//                        case BLANK:
//                            break;
//                        case BOOLEAN:
//                            break;
//                        case ERROR:
//                            break;
//                        default:
//                            break;
//                    }
//
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
