package org.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class fieldGenerationInExcel {
    public void createStatusCells(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(0);
        XSSFCell lastStatus = row.createCell(4);
        XSSFCell actualStatus = row.createCell(5);

        lastStatus.setCellValue("Estado Anterior");
        actualStatus.setCellValue("Estado Actual");

        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
    }
}