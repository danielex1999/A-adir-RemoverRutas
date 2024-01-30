package org.bimbo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;

public class AsignarCliente {
    public void AsignacionCliente(XSSFRow row, WebDriver driver){
        //Insertando valores de Agencia, Codigo, Ruta Preventa, Status
        XSSFCell Agencia = row.getCell(1);
        XSSFCell Codigo = row.getCell(2);
        XSSFCell RutaPreventa = row.getCell(4);
        XSSFCell Status = row.getCell(7);
        double rutaDouble = Double.parseDouble(RutaPreventa.getStringCellValue());
        String valorRuta = String.format("%04d", (int) rutaDouble);
        System.out.println(Agencia);
        System.out.println(Codigo);
        System.out.println(valorRuta);
        System.out.println(Status);
    }
}
