package org.bimbo;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excel.GeneracionCampos;
import org.google.Login;
import org.google.RegistroCliente;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String perfilOriginal = "C:\\Users\\danie\\AppData\\Local\\Google\\Chrome\\User Data";
        String rutaExcel = "C:\\Users\\danie\\OneDrive\\Escritorio\\ASIGNACION.xlsx";
        FileInputStream fis = new FileInputStream(rutaExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        GeneracionCampos generacionCampos = new GeneracionCampos();
        AsignarCliente asignarCliente= new AsignarCliente();
        XSSFSheet sheet = workbook.getSheetAt(0);
        Login login = new Login();
        RegistroCliente registroCliente = new RegistroCliente();
        int filaInicio = 2, filaFinal = 10;
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--user-data-dir=" + perfilOriginal);
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\danie\\\\Documents\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(opciones);
        login.InicioSesion(driver);
        generacionCampos.CreacionCeldaFila(sheet);
        registroCliente.IngresoCentrodeVentas(driver);

        //---
        for (int i = filaInicio; i <= filaFinal; i++) {
            XSSFRow row = sheet.getRow(i - 1);
            asignarCliente.AsignacionCliente(row,driver);
            System.out.println("------------------");
        }

        workbook.close();
        driver.quit();

    }


    private static void saveWorkbook(XSSFWorkbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }
}