package org.bakery;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excel.fieldGenerationInExcel;
import org.excel.saveWorkbook;
import org.google.Login;
import org.google.clientCategory;
import org.methods.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class clientAssignment {

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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + properties.getProperty("chrome-options"));

        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver-path"));
        WebDriver driver = new ChromeDriver(options);

        FileInputStream fis = new FileInputStream(properties.getProperty("excel-path-file") + properties.getProperty("excel-file") + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        fieldGenerationInExcel fieldGenerationInExcel = new fieldGenerationInExcel();
        methodsFunctionalityBankery methodsFunctionalityBankery = new methodsFunctionalityBankery();
        saveWorkbook saveWorkbook = new saveWorkbook();
        getCurrentTime getCurrentTime = new getCurrentTime();
        Login login = new Login();
        clientCategory clientCategory = new clientCategory();

        fieldGenerationInExcel.createStatusCells(sheet);
        login.LoginToMC1(driver);
        clientCategory.toSalesCenter(driver);

        for (int i = Integer.parseInt(properties.getProperty("clienteInicial")); i <= Integer.parseInt(properties.getProperty("clienteFinal")); i++) {
            XSSFRow row = sheet.getRow(i - 1);
            getCurrentTime.setTime(i);
            methodsFunctionalityBankery.ClientAssignment(row, driver);
            System.out.println("---------------------------------");
            saveWorkbook.saveExcelPerClient(workbook, fis.toString());
        }
        workbook.close();
        driver.quit();
    }
}
