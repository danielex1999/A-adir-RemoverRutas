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
import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Rutas
        String perfilOriginal = "C:\\Users\\danie\\AppData\\Local\\Google\\Chrome\\User Data";
        String rutaExcel = "C:\\Users\\danie\\OneDrive\\Escritorio\\RETIRAR RUTA SJL (2).xlsx";

        // Configuración del WebDriver
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--user-data-dir=" + perfilOriginal);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danie\\Documents\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(opciones);

        // Lectura del archivo Excel
        FileInputStream fis = new FileInputStream(rutaExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Instancias de clases
        GeneracionCampos generacionCampos = new GeneracionCampos();
        AsignarCliente asignarCliente = new AsignarCliente();
        RetirarCliente retirarCliente=new RetirarCliente();
        Login login = new Login();
        RegistroCliente registroCliente = new RegistroCliente();

        // Creación de celdas y fila en la hoja de Excel
        generacionCampos.CreacionCeldaFila(sheet);


        //------------------------------------------------------------------
        login.InicioSesion(driver);
        registroCliente.IngresoCentrodeVentas(driver);

        int filaInicio = 2, filaFinal = 487;
        for (int i = filaInicio; i <= filaFinal; i++) {
            //Tiempo
            LocalDateTime locaDate = LocalDateTime.now();
            int hours  = locaDate.getHour();
            int minutes = locaDate.getMinute();
            int seconds = locaDate.getSecond();
            String formattedTime = String.format("[%02d:%02d:%02d]", hours, minutes, seconds);
            XSSFRow row = sheet.getRow(i - 1);
            System.out.println(formattedTime+" Se esta realizando la fila "+i);
            retirarCliente.RetiradaCliente(row, driver);
            //asignarCliente.AsignacionCliente(row,driver);
            System.out.println("---------------------------------");
            saveWorkbook(workbook, rutaExcel);
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
