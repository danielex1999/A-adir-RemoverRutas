package org.bakery;

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
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class Main {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = Login.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String nameExcelFile = "ENTRE LUN MAR MIE";
        int filaInicio = 2, filaFinal = 515;

        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--user-data-dir=" + properties.getProperty("chrome-options"));

        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver-path"));
        WebDriver driver = new ChromeDriver(opciones);

        FileInputStream fis = new FileInputStream(properties.getProperty("excel-path-file") + nameExcelFile + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        GeneracionCampos generacionCampos = new GeneracionCampos();
        AsignarCliente asignarCliente = new AsignarCliente();
        RetirarCliente retirarCliente = new RetirarCliente();
        Login login = new Login();
        RegistroCliente registroCliente = new RegistroCliente();
        generacionCampos.CreacionCeldaFila(sheet);

        login.InicioSesion(driver);
        registroCliente.IngresoCentrodeVentas(driver);

        for (int i = filaInicio; i <= filaFinal; i++) {
            LocalDateTime locaDate = LocalDateTime.now();
            int hours = locaDate.getHour();
            int minutes = locaDate.getMinute();
            int seconds = locaDate.getSecond();
            String formattedTime = String.format("[%02d:%02d:%02d]", hours, minutes, seconds);
            XSSFRow row = sheet.getRow(i - 1);
            System.out.println(formattedTime + " Se esta realizando la fila " + i);
            //retirarCliente.RetiradaCliente(row, driver);
            asignarCliente.AsignacionCliente(row, driver);
            System.out.println("---------------------------------");
            saveWorkbook(workbook, properties.getProperty("excel-path-file") + nameExcelFile + ".xlsx");
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