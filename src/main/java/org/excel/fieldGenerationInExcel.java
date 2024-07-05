package org.excel;


import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class fieldGenerationInExcel {
    public void createStatusCells(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(0);
        XSSFCell lastStatus = row.createCell(4);
        XSSFCell actualStatus = row.createCell(5);

        lastStatus.setCellStyle(createCenteredStyle(workbook));
        actualStatus.setCellStyle(createCenteredStyle(workbook));
        lastStatus.setCellValue("Estado Anterior");
        actualStatus.setCellValue("Estado Actual");

        sheet.setColumnWidth(4, 18 * 256);
        sheet.setColumnWidth(5, 18 * 256);
    }

    public void generateRouteSheet(XSSFWorkbook workbook, String filePath) throws IOException {
        XSSFSheet sheet = workbook.createSheet("Rutas Activas");
        XSSFRow row = sheet.createRow(0);

        XSSFCell agencia = row.createCell(1);
        XSSFCell codigo = row.createCell(2);
        XSSFCell rutasActivas = row.createCell(3);

        agencia.setCellStyle(createCenteredStyle(workbook));
        codigo.setCellStyle(createCenteredStyle(workbook));
        rutasActivas.setCellStyle(createCenteredStyle(workbook));
        agencia.setCellValue("Agencia");
        codigo.setCellValue("Codigo");
        rutasActivas.setCellValue("Rutas Activas");

        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 20 * 256);

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    public XSSFCellStyle createCenteredStyle(XSSFWorkbook workbook) {
        XSSFCellStyle centeredStyle = workbook.createCellStyle();
        centeredStyle.setAlignment(HorizontalAlignment.CENTER);
        return centeredStyle;
    }

    public void createSizeRouteStyle(XSSFSheet sheet, int i) {
        sheet.setColumnWidth(i, 20 * 256);
    }
}