package com.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/test/resources/data/users.xlsx");
        
        // Read existing workbook
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            workbook = new XSSFWorkbook(fis);
        }
        
        Sheet sheet = workbook.getSheet("users");
        if (sheet == null) {
            sheet = workbook.createSheet("users");
        }
        
        // Check if header exists
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            headerRow = sheet.createRow(0);
        }
        
        // Update header with all required columns
        String[] headers = {
            "name", "email", "password", "title", "birth_date", "birth_month",
            "birth_year", "firstname", "lastname", "company", "address1",
            "address2", "country", "zipcode", "state", "city", "mobile_number"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null) {
                cell = headerRow.createCell(i);
            }
            cell.setCellValue(headers[i]);
        }
        
        // Check if data row exists
        Row dataRow = sheet.getRow(1);
        if (dataRow == null) {
            dataRow = sheet.createRow(1);
        }
        
        // Update sample data
        String[] sampleData = {
            "testuser", "testnew1782@gmail.com", "password123", "Mr", "15", "5",
            "1990", "John", "Doe", "ABC Corp", "123 Main St", "Apt 4B",
            "United States", "12345", "California", "Los Angeles", "1234567890"
        };
        
        for (int i = 0; i < sampleData.length; i++) {
            Cell cell = dataRow.getCell(i);
            if (cell == null) {
                cell = dataRow.createCell(i);
            }
            cell.setCellValue(sampleData[i]);
        }
        
        // Write back to file
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            workbook.write(fos);
        }
        
        workbook.close();
        System.out.println("Excel file updated with new columns and sample data.");
    }
}